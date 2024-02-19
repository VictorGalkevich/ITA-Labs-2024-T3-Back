package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LotService {
    private final LotRepository lotRepository;
    private final LotMapper lotMapper;

    public List<LotReadDTO> findAll() {
        return lotRepository.findAll().stream()
                .map(lotMapper::toLotReadDTO)
                .toList();
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

    public List<LotReadDTO> findLotsByCategoryId(Integer id) {
        return lotRepository.findAllByCategoryId(id).stream()
                .map(lotMapper::toLotReadDTO)
                .toList();
    }
}
