package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.BidCreateDto;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Page<BidReadDto> findBidsByUserId(Long id, Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return bidRepository.findAllByUserId(id, req)
                .map(bidMapper::toReadDto);
    }

    private void setOverbidForLot(Bid bid) {
        Long lotId = bid.getLotId();
        Optional<Lot> byId = lotRepository.findById(lotId);
        byId.ifPresent(it -> {
            it.setBidQuantity(it.getBidQuantity() + 1);
        });
        Optional<Bid> byLotId = bidRepository.findByLotIdAndStatus(lotId, BidStatus.LEADING);
        byLotId.ifPresent(it -> it.setStatus(BidStatus.OVERBID));
    }

    private void deleteOldBidFromUser(Bid bid) {
        Long userId = bid.getUserId();
        Long lotId = bid.getLotId();
        Optional<Bid> byUserId = bidRepository.findByUserIdAndLotId(userId, lotId);
        byUserId.ifPresent(bidRepository::delete);
    }
}


