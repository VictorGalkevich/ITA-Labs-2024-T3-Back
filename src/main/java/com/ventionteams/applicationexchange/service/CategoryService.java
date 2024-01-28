package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.mapper.CategoryMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryReadDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toReadDto)
                .toList();
    }

    public Optional<CategoryReadDto> findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toReadDto);
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
                .map(categoryMapper::toCategory)
                .map(categoryRepository::save)
                .map(categoryMapper::toReadDto)
                .orElseThrow();
    }

    public Optional<CategoryReadDto> update(Integer id, CategoryCreateEditDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> categoryMapper.toCategory(categoryDto))
                .map(categoryRepository::update)
                .map(categoryMapper::toReadDto);
    }
}
