package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.dto.SubcategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryReadMapper implements Mapper<Category, CategoryReadDto> {

    private final SubcategoryReadMapper subcategoryReadMapper;

    @Override
    public CategoryReadDto map(Category object) {
        List<SubcategoryReadDto> dto = Optional.ofNullable(object.getSubcategories())
                .map(entity -> entity.stream()
                        .map(subcategoryReadMapper::map)
                        .toList())
                .orElse(new ArrayList<>());
        return new CategoryReadDto(
                object.getId(),
                object.getName(),
                dto
        );
    }
}
