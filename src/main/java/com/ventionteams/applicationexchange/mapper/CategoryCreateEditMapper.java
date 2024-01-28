package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfig;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Category;
import org.mapstruct.Mapper;

@Mapper(uses = SubcategoryCreateEditMapper.class, config = MapperConfig.class)
public interface CategoryCreateEditMapper{
    Category map(CategoryCreateEditDto object);
}
