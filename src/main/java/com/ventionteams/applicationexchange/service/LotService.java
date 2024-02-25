package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.specification.LotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@TransactionalService
@RequiredArgsConstructor
public class LotService {
    private final LotRepository lotRepository;
    private final LotMapper lotMapper;

    public Page<LotReadDTO> findAll(Integer page, Integer limit, LotFilterDTO filter, LotSortCriteria sort) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        Specification<Lot> specification = LotSpecification.getFilterSpecification(filter);
        return lotRepository.findAll(specification, req)
                .map(lotMapper::toLotReadDTO);
    }

    public Optional<LotReadDTO> findById(Long id) {
        return lotRepository.findById(id)
                .map(lotMapper::toLotReadDTO);
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
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO)
                .orElseThrow();
    }

    @Transactional
    public Optional<LotReadDTO> update(Long id, LotUpdateDTO dto) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotMapper.map(lot, dto);
                    return lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    public Page<LotReadDTO> findLotsByCategoryId(Integer id, Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return lotRepository.findAllByCategoryId(id, req)
                .map(lotMapper::toLotReadDTO);
    }
}
