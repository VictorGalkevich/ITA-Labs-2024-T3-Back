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
import com.ventionteams.applicationexchange.exception.LotNotFoundException;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.ventionteams.applicationexchange.entity.enumeration.BidStatus.LEADING;
import static com.ventionteams.applicationexchange.entity.enumeration.BidStatus.OVERBID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

        lotRepository.findById(lotId)
                .ifPresentOrElse(lot -> {
                    if (bidsRestricted(lot)) {
                        throw new AuctionEndedException("No more bids allowed, max bid has already been done",
                                BAD_REQUEST);
                    }

                    lot.setBidQuantity(lot.getBidQuantity() + 1);

                    bidRepository.findByLotIdAndStatus(lotId, LEADING)
                            .ifPresentOrElse(prevBid -> {
                                long bidAmount = bid.getAmount();
                                long startPrice = lot.getStartPrice();
                                long totalPrice = lot.getTotalPrice();

                                if (startPrice < bidAmount && bidAmount <= totalPrice - 1) {
                                    prevBid.setStatus(OVERBID);
                                    if (bidAmount == totalPrice - 1) {
                                        lot.setStatus(LotStatus.AUCTION_ENDED);
                                    }
                                } else {
                                    String msg = "Price %s is not less than current start price (%s)";
                                    long val = startPrice;
                                    if (bidAmount > totalPrice - 1) {
                                        msg = "Price %s is bigger than current max price (%s)";
                                        val = totalPrice - 1;
                                    }
                                    throw new IllegalPriceException(
                                            String.format(msg, bidAmount, val),
                                            BAD_REQUEST
                                    );
                                }
                            }, () -> lot.setStartPrice(bid.getAmount() + 1));
                }, () -> {
                    throw new LotNotFoundException(
                            "lot with id %d doesn't exist".formatted(lotId),
                            NOT_FOUND);
                });
    }

    private void deleteOldBidFromUser(Bid bid) {
        UUID userId = bid.getUserId();
        Long lotId = bid.getLotId();
        Optional<Bid> byUserId = bidRepository.findByUserIdAndLotId(userId, lotId);
        byUserId.ifPresent(bidRepository::delete);
    }

    private boolean bidsRestricted(Lot lot) {
        return !lot.getStatus().equals(LotStatus.ACTIVE) ||
               lot.getExpirationDate().isBefore(Instant.now());
    }
}


