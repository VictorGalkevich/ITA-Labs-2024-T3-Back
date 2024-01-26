package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.model.Lot;
import com.ventionteams.applicationexchange.repository.LotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotService {
    private final LotRepository lotRepository;
    private LotMapper lotMapper;

    public LotService(LotRepository lotRepository, LotMapper lotMapper) {
        this.lotRepository = lotRepository;
        this.lotMapper = lotMapper;
    }

    public List<LotReadDTO> findAll() {
        return lotMapper.toLotReadList(lotRepository.findAll());
    }

    public Optional<LotReadDTO> findById(Integer id) {
        return Optional.ofNullable(lotMapper.toLotReadDTO(lotRepository.findById(id)));
    }

    public Lot create(Lot lot) {
        return lotRepository.save(lot);
        //return lotMapper.toLotReadDTO(lotRepository.save(lot));
    }

    public void delete(Integer id) {
        lotRepository.delete(id);
    }

    public LotReadDTO update(Integer id, LotUpdateDTO lotUpdateDTO) {
        return lotMapper.toLotReadDTO(lotRepository.update(id, lotUpdateDTO));
    }
}
