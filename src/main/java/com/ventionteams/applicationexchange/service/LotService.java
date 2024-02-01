package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.Subcategory;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import com.ventionteams.applicationexchange.repository.LocationRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LotService {
    private final LotRepository lotRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final LotMapper lotMapper;

    public List<LotReadDTO> findAll() {
        return lotRepository.findAll().stream()
                .map(lotMapper::toLotReadDTO)
                .toList();
    }

    public Optional<LotReadDTO> findById(Integer id) {
        return lotRepository.findById(id)
                .map(lotMapper::toLotReadDTO);
    }

    @Transactional
    public boolean delete(Integer id) {
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
                .map(this::saveOrUpdate)
                .map(lotMapper::toLotReadDTO)
                .orElseThrow();
    }

    @Transactional
    public Optional<LotReadDTO> update(Integer id, LotUpdateDTO dto) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotMapper.map(lot, dto);
                    return lot;
                })
                .map(this::saveOrUpdate)
                .map(lotMapper::toLotReadDTO);
    }

    private Lot saveOrUpdate(Lot obj) {
        Location location = obj.getLocation();
        Category category = categoryRepository.findById(obj.getCategory().getId()).get();
        Subcategory subcategory = subcategoryRepository.findById(obj.getSubcategory().getId()).get();
        obj.setLocation(null);
        obj.setCategory(null);
        obj.setSubcategory(null);
        if (obj.getCreatedAt() == null) {
            obj.setExpirationDate(Instant.now().plusSeconds(86400 * 7 + 60));
        }
        lotRepository.save(obj);
        location.addLot(obj);
        category.addLot(obj);
        subcategory.addLot(obj);
        subcategoryRepository.saveAndFlush(subcategory);
        return obj;
    }

    public List<LotReadDTO> findLotsByCategoryId(Integer id) {
        return lotRepository.findAllByCategoryId(id).stream()
                .map(lotMapper::toLotReadDTO)
                .toList();
    }
}
