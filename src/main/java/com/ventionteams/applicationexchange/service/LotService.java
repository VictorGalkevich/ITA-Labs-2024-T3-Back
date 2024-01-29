package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.repository.LotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotService {
    private final LotRepository lotRepository;
    private final LotMapper lotMapper;

    public LotService(LotRepository lotRepository, LotMapper lotMapper) {
        this.lotRepository = lotRepository;
        this.lotMapper = lotMapper;
    }

    public List<LotReadDTO> findAll() {
        return lotMapper.toLotReadList(lotRepository.findAll());
    }

    public Optional<LotReadDTO> findById(Integer id) {
        return lotRepository.findById(id)
                .map(lotMapper::toLotReadDTO);
    }

    public LotReadDTO create(Lot lot) {
        return lotMapper.toLotReadDTO(lotRepository.save(lot));
    }

    public boolean delete(Integer id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotRepository.delete(id);
                    return true;
                })
                .orElse(false);
    }

    public Optional<LotReadDTO> update(Integer id, LotUpdateDTO lotUpdateDTO) {
        return lotRepository.findById(id)
                .map(lot -> lotMapper.toLotReadDTO(lotRepository.update(id, lotUpdateDTO)));
    }
}
