package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.create.LotUpdateDTO;
import com.ventionteams.applicationexchange.dto.read.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = {BidMapper.class, LocationMapper.class})
public interface LotMapper {

    @Mapping(target = "categoryId", source = "lot.category.id")
    @Mapping(target = "category", source = "lot.category.name")
    LotReadDTO toLotReadDTO(Lot lot);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.categoryId()).build())")
    @Mapping(target = "expirationDate", expression = "java(java.time.Instant.now().plus(dto.days(), java.time.temporal.ChronoUnit.DAYS))")
    @Mapping(target = "pricePerUnit", expression = "java((double) dto.totalPrice() / dto.quantity())")
    Lot toLot(LotUpdateDTO dto);

    void map(@MappingTarget Lot to, LotUpdateDTO from);

    @Mapping(target = "category", expression = "java(Category.builder().id(from.getCategoryId()).build())")
    void map(@MappingTarget Lot to, LotReadDTO from);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.getCategoryId()).build())")
    @Mapping(target = "expirationDate", expression = "java(java.time.Instant.now().plusSeconds(86400 * 7 + 60))")
    Lot toLot(LotReadDTO dto);

}
