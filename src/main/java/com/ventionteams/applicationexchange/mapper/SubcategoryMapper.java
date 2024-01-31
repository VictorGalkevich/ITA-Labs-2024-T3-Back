package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.SubcategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.SubcategoryReadDto;
import com.ventionteams.applicationexchange.entity.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface SubcategoryMapper {
    @Mapping(target = "categoryId", source = "category.id")
    SubcategoryReadDto toReadDto(Subcategory subcategory);

    @Mapping(target = "id", ignore = true)
    Subcategory toSubcategory(SubcategoryCreateEditDto dto);
}
