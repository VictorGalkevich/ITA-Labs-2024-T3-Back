package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Category;
import org.mapstruct.Mapper;

@Mapper(uses = SubcategoryCreateEditMapper.class, componentModel = "spring")
public interface CategoryCreateEditMapper{
    Category map(CategoryCreateEditDto object);
}
