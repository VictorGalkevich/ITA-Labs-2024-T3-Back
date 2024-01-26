package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.mapper.CategoryCreateEditMapper;
import com.ventionteams.applicationexchange.mapper.CategoryReadMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryReadMapper categoryReadMapper;
    private final CategoryCreateEditMapper categoryCreateEditMapper;

    public List<CategoryReadDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryReadMapper::map)
                .toList();
    }

    public Optional<CategoryReadDto> findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryReadMapper::map);
    }

    public boolean delete(Integer id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return true;
                })
                .orElse(false);
    }

    public CategoryReadDto create(CategoryCreateEditDto categoryDto) {
        return Optional.of(categoryDto)
                .map(categoryCreateEditMapper::map)
                .map(categoryRepository::save)
                .map(categoryReadMapper::map)
                .orElseThrow();
    }

    public Optional<CategoryReadDto> update(Integer id, CategoryCreateEditDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> categoryCreateEditMapper.map(categoryDto))
                .map(categoryRepository::update)
                .map(categoryReadMapper::map);
    }
}
