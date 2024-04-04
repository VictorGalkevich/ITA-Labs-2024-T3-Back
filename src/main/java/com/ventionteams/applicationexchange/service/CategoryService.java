package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.read.CategoryReadDto;
import com.ventionteams.applicationexchange.dto.read.MainPageCategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.CategoryMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@TransactionalService
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ImageService imageService;

    public List<MainPageCategoryReadDto> findAll() {
        return categoryRepository.findAllByParentIdIsNull().stream()
                .map(categoryMapper::toMainPageReadDto)
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
                    imageService.deleteImage(category.getImageUrl());
                    categoryRepository.delete(category);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public CategoryReadDto create(CategoryCreateEditDto categoryDto, MultipartFile image) {
        return Optional.of(categoryDto)
                .map(categoryMapper::toCategory)
                .map(category -> {
                    Category parent = categoryDto.parentId() == null ? null : categoryRepository.findById(categoryDto.parentId()).get();
                    category.setParent(parent);
                    Image newImage = imageService.saveSingleImage(image, "category");
                    if (image != null)
                        category.setImageUrl(newImage.getUrl());
                    return category;
                })
                .map(categoryRepository::save)
                .map(categoryMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<CategoryReadDto> update(Integer id, CategoryCreateEditDto categoryDto, MultipartFile newImage) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryMapper.map(category, categoryDto);
                    Category parent = categoryDto.parentId() == null ? null : categoryRepository.findById(categoryDto.parentId()).get();
                    category.setParent(parent);
                    imageService.deleteImage(category.getImageUrl());
                    Image image = imageService.saveSingleImage(newImage, "category");
                    if (image != null) {
                        category.setImageUrl(image.getUrl());
                    } else {
                        category.setImageUrl(null);
                    }
                    return category;
                })
                .map(categoryRepository::save)
                .map(categoryMapper::toReadDto);
    }
}
