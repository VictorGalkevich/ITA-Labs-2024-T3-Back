package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.mapper.CategoryMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryReadDto> findAll() {
        return categoryRepository.findAllByParentIdIsNull().stream()
                .map(categoryMapper::toReadDto)
                .toList();
    }

    public Optional<CategoryReadDto> findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public CategoryReadDto create(CategoryCreateEditDto categoryDto) {
        return Optional.of(categoryDto)
                .map(categoryMapper::toCategory)
                .map(category -> {
                    Category parent = categoryDto.parentId() == null ? null : categoryRepository.findById(categoryDto.parentId()).get();
                    category.setParent(parent);
                    return category;
                })
                .map(categoryRepository::save)
                .map(categoryMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<CategoryReadDto> update(Integer id, CategoryCreateEditDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryMapper.map(category, categoryDto);
                    Category parent = categoryDto.parentId() == null ? null : categoryRepository.findById(categoryDto.parentId()).get();
                    category.setParent(parent);
                    return category;
                })
                .map(categoryRepository::save)
                .map(categoryMapper::toReadDto);
    }
}
