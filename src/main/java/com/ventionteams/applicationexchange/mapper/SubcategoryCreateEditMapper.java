package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfig;
import com.ventionteams.applicationexchange.dto.SubcategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Subcategory;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SubcategoryCreateEditMapper{
    Subcategory map(SubcategoryCreateEditDto object);
}
