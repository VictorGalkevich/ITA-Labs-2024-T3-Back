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
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "totalPrice", expression = "java(lot.getPricePerUnit() * lot.getQuantity())")
    LotReadDTO toLotReadDTO(Lot lot);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.categoryId()).build())")
    @Mapping(target = "expirationDate", expression = "java(Instant.now().plusSeconds(86400 * 7 + 60))")
    Lot toLot(LotUpdateDTO dto);

    void map(@MappingTarget Lot to, LotUpdateDTO from);
}
