package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.dto.MainPageCategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryCreateEditDto dto);

    @Mapping(target = "parentId", source = "parent.id")
    CategoryReadDto toReadDto(Category category);

    @Mapping(target = "parentId", source = "parent.id")
    MainPageCategoryReadDto toMainPageReadDto(Category category);

    void map(@MappingTarget Category to, CategoryCreateEditDto from);
}
