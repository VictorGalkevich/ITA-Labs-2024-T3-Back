package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Duration;
import java.time.Instant;

@Mapper(config = MapperConfiguration.class, uses = {BidMapper.class, LocationMapper.class})
public interface LotMapper {

    @Mapping(target = "categoryId", source = "lot.category.id")
    @Mapping(target = "category", source = "lot.category.name")
    @Mapping(target = "expiresThrough", expression = "java(distance(lot.getExpirationDate()))")
    LotReadDTO toLotReadDTO(Lot lot);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.categoryId()).build())")
    @Mapping(target = "expirationDate", expression = "java(java.time.Instant.now().plus(dto.days(), java.time.temporal.ChronoUnit.DAYS))")
    @Mapping(target = "totalPrice", expression = "java(dto.pricePerUnit() * dto.quantity())")
    Lot toLot(LotUpdateDTO dto);

    void map(@MappingTarget Lot to, LotUpdateDTO from);

    @Mapping(target = "category", expression = "java(Category.builder().id(dto.getCategoryId()).build())")
    @Mapping(target = "expirationDate", expression = "java(java.time.Instant.now().plusSeconds(86400 * 7 + 60))")
    Lot toLot(LotReadDTO dto);

    default Duration distance(Instant a) {
        return Duration.between(a, Instant.now());
    }
}
