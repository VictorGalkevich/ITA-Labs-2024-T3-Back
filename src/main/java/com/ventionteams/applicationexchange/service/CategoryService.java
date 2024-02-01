package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Subcategory;
import com.ventionteams.applicationexchange.mapper.CategoryMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import com.ventionteams.applicationexchange.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
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
                .map(this::saveOrUpdate)
                .map(categoryMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<CategoryReadDto> update(Integer id, CategoryCreateEditDto categoryDto) {
        return categoryRepository.findById(id)
                .map(obj -> {
                    categoryMapper.map(obj, categoryDto);
                    return obj;
                })
                .map(this::saveOrUpdate)
                .map(categoryMapper::toReadDto);
    }

    private Category saveOrUpdate(Category obj) {
        List<Subcategory> scs = obj.getSubcategories();
        obj.setSubcategories(new ArrayList<>());
        categoryRepository.save(obj);
        for (Subcategory sc : scs) {
            obj.addSubcategory(sc);
        }
        subcategoryRepository.saveAllAndFlush(scs);
        return obj;
    }
}
