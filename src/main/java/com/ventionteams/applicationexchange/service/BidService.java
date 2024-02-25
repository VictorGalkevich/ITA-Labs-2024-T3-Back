package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.BidCreateDto;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.dto.BidUserReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@TransactionalService
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;
    private final UserRepository userRepository;
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
                    setNewLeadingBidToLot(bid);
                    setNewBidToUser(bid);
                    return bidRepository.save(bid);
                })
                .map(bidMapper::toReadDto)
                .orElseThrow();
    }

    public Page<BidUserReadDto> findBidsByUserId(Long id, Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return bidRepository.findAllByUserId(id, req)
                .map(bidMapper::toUserReadDto);
    }

    private void setNewLeadingBidToLot(Bid bid) {
        Optional<Lot> lot = lotRepository.findById(bid.getLot().getId());
        lot.ifPresent(it -> {
            if (it.getBid() != null) {
                it.getBid().setStatus(BidStatus.OVERBID);
            }
            it.setBid(bid);
        });
    }

    private void setNewBidToUser(Bid bid) {
        Optional<User> user = userRepository.findById(bid.getUser().getId());
        user.ifPresent(it -> {
            List<Bid> bids = it.getBids();
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getLot().getId().equals(bid.getLot().getId())) {
                    bids.remove(i);
                    break;
                }
            }
            bids.add(bid);
        });
    }
}


