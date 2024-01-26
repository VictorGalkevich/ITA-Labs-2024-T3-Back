package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import org.mapstruct.Mapper;

@Mapper(uses = SubcategoryReadMapper.class, componentModel = "spring")
public interface CategoryReadMapper{
    CategoryReadDto map(Category object);
}
