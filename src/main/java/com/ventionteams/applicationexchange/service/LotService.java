package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.specification.LotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@TransactionalService
@RequiredArgsConstructor
public class LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final LotMapper lotMapper;
    private final BidMapper bidMapper;

    public Page<LotReadDTO> findAll(Integer page, Integer limit, LotFilterDTO filter, LotSortCriteria sort, Long userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        Specification<Lot> specification = LotSpecification.getFilterSpecification(filter);
        return lotRepository.findAll(specification, req)
                .map(lot -> map(lot, userId));
    }

    public Optional<LotReadDTO> findById(Long lotId, Long userId) {
        return lotRepository.findById(lotId)
                .map(lot -> map(lot, userId));
    }

    @Transactional
    public boolean delete(Long id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotRepository.delete(lot);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public LotReadDTO create(LotUpdateDTO dto) {
        return Optional.of(dto)
                .map(lotMapper::toLot)
                .map(x -> {
                    x.setBidQuantity(0);
                    return x;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO)
                .orElseThrow();
    }

    @Transactional
    public Optional<LotReadDTO> update(Long id, LotUpdateDTO dto, Long userId) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotMapper.map(lot, dto);
                    return lot;
                })
                .map(lotRepository::save)
                .map(lot -> map(lot, userId));
    }

    public Page<LotReadDTO> findLotsByCategoryId(Integer id, Integer page, Integer limit, Long userId) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return lotRepository.findAllByCategoryId(id, req)
                .map(lot -> map(lot, userId));
    }

    private LotReadDTO map(Lot lot, Long userId) {
        LotReadDTO lotReadDTO = lotMapper.toLotReadDTO(lot);
        BidReadDto leading = bidMapper.toReadDto(bidRepository.findByLotIdAndStatus(lot.getId(), BidStatus.LEADING).orElse(null));
        Optional<Bid> bid = bidRepository.findByUserIdAndLotId(userId, lot.getId());
        BidReadDto users = bidMapper.toReadDto(bid.orElse(null));
        lotReadDTO.setLeading(leading);
        lotReadDTO.setUsers(users);
        return lotReadDTO;
    }
}
