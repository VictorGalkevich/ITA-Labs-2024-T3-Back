package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.BidCreateDto;
import com.ventionteams.applicationexchange.dto.BidForUserDto;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.exception.AuctionEndedException;
import com.ventionteams.applicationexchange.exception.IllegalPriceException;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@TransactionalService
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;
    private final BidMapper bidMapper;

    public Page<BidReadDto> findAll(Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return bidRepository.findAll(req)
                .map(bidMapper::toReadDto);
    }

    public Optional<BidReadDto> findById(Long id) {
        return bidRepository.findById(id)
                .map(bidMapper::toReadDto);
    }

    @Transactional
    public BidReadDto create(BidCreateDto dto) {
        return Optional.of(dto)
                .map(bidMapper::toBid)
                .map(bid -> {
                    setOverbidForLot(bid);
                    deleteOldBidFromUser(bid);
                    return bidRepository.save(bid);
                })
                .map(bidMapper::toReadDto)
                .orElseThrow();
    }

    public Page<BidForUserDto> findBidsByUserId(UUID id, Integer page, Integer limit, BidStatus status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return bidRepository.findAllByUserIdAndStatus(id, req, status);
    }

    private void setOverbidForLot(Bid bid) {
        Long lotId = bid.getLotId();
        Optional<Lot> byId = lotRepository.findById(lotId);
        byId.ifPresent(it -> it.setBidQuantity(it.getBidQuantity() + 1));
        Optional<Bid> byLotId = bidRepository.findByLotIdAndStatus(lotId, BidStatus.LEADING);
        byId.ifPresent(it -> {
            if (it.getStatus().equals(LotStatus.AUCTION_ENDED)) {
                throw new AuctionEndedException("No more bids allowed, max bid has already been done",
                        HttpStatus.BAD_REQUEST);
            }
            byLotId.ifPresent(prev -> {
                if ((double) it.getStartPrice() < bid.getAmount() && (double) bid.getAmount() <= it.getTotalPrice() - 1) {
                    it.setBidQuantity(it.getBidQuantity() + 1);
                    it.setStartPrice((double) bid.getAmount() + 1);
                    prev.setStatus(BidStatus.OVERBID);
                    if ((double) bid.getAmount() == it.getTotalPrice() - 1) {
                        it.setStatus(LotStatus.AUCTION_ENDED);
                    }
                } else {
                    String msg = "Price %s is not less than current start price (%s)";
                    Double val = it.getStartPrice();
                    if ((double) bid.getAmount() > it.getTotalPrice() - 1) {
                        msg = "Price %s is bigger than current max price (%s)";
                        val = it.getTotalPrice() - 1;
                    }
                    throw new IllegalPriceException(
                            msg.formatted(
                                    bid.getAmount(),
                                    val),
                            HttpStatus.BAD_REQUEST
                    );
                }
            });
        });
        byLotId.ifPresent(it -> it.setStatus(BidStatus.OVERBID));
    }

    private void deleteOldBidFromUser(Bid bid) {
        UUID userId = bid.getUserId();
        Long lotId = bid.getLotId();
        Optional<Bid> byUserId = bidRepository.findByUserIdAndLotId(userId, lotId);
        byUserId.ifPresent(bidRepository::delete);
    }
}


