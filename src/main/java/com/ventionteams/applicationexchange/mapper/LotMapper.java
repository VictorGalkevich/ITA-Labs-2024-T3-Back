package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface LotMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "subcategoryId", source = "subcategory.id")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "subcategory", source = "subcategory.name")
    LotReadDTO toLotReadDTO(Lot lot);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.categoryId()).build())")
    @Mapping(target = "subcategory", expression = "java(Subcategory.builder().id(dto.subcategoryId()).build())")
    Lot toLot(LotUpdateDTO dto);

    @Mapping(target = "category", expression = "java(Category.builder().id(from.categoryId()).build())")
    @Mapping(target = "subcategory", expression = "java(Subcategory.builder().id(from.subcategoryId()).build())")
    void map(@MappingTarget Lot to, LotUpdateDTO from);
}
