package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfig;
import com.ventionteams.applicationexchange.dto.SubcategoryReadDto;
import com.ventionteams.applicationexchange.entity.Subcategory;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SubcategoryReadMapper{
    SubcategoryReadDto map(Subcategory object);
}
