package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryCreateEditMapper implements Mapper<CategoryCreateEditDto, Category>{

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryCreateEditMapper subcategoryCreateEditMapper;
    @Override
    public Category map(CategoryCreateEditDto object) {
        Category category = new Category();
        copy(object, category);
        category.setId(object.getId());
        return category;
    }

    @Override
    public Category map(CategoryCreateEditDto from, Category to) {
        copy(from, to);
        return to;
    }

    private void copy(CategoryCreateEditDto dto, Category category) {
        category.setName(dto.getName());

        Optional.ofNullable(dto.getSubcategories())
                .map(item -> item.stream().map(subcategoryCreateEditMapper::map).toList())
                .ifPresent(category::setSubcategories);
    }
}
