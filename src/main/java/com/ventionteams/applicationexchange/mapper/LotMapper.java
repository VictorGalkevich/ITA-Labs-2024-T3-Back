package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface LotMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "subcategoryId", source = "subcategory.id")
    LotReadDTO toLotReadDTO(Lot lot);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.categoryId()).build())")
    @Mapping(target = "subcategory", expression = "java(Subcategory.builder().id(dto.subcategoryId()).build())")
    Lot toLot(LotUpdateDTO dto);

    default Lot map(Lot to, Lot from) {
        to.setSubcategory(from.getSubcategory());
        to.setCategory(from.getCategory());
        to.setLocation(from.getLocation());
        to.setDescription(from.getDescription());
        to.setQuantity(from.getQuantity());
        to.setStatus(from.getStatus());
        to.setTitle(from.getTitle());
        to.setImageUrl(from.getImageUrl());
        to.setPricePerUnit(from.getPricePerUnit());
        return to;
    }
}
