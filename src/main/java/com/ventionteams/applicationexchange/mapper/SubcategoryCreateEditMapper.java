package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.SubcategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Subcategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubcategoryCreateEditMapper{
    Subcategory map(SubcategoryCreateEditDto object);
}
