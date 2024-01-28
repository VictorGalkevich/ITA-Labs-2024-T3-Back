package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfig;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import org.mapstruct.Mapper;

@Mapper(uses = {SubcategoryMapper.class},
        config = MapperConfig.class)
public interface CategoryMapper {
    Category toCategory(CategoryCreateEditDto dto);

    CategoryReadDto toReadDto(Category category);
}
