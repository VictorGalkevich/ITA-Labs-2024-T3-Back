package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {SubcategoryMapper.class},
        config = MapperConfiguration.class)
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryCreateEditDto dto);

    CategoryReadDto toReadDto(Category category);

    void map(@MappingTarget Category to, CategoryCreateEditDto from);
}
