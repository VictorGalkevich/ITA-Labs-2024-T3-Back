package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.create.RequestCreateEditDto;
import com.ventionteams.applicationexchange.dto.read.RequestReadDto;
import com.ventionteams.applicationexchange.entity.PurchaseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface RequestMapper {

    RequestReadDto toReadDto(PurchaseRequest request);

    @Mapping(target = "category", expression = "java(com.ventionteams.applicationexchange.entity.Category.builder().id(dto.categoryId()).build())")
    @Mapping(target = "expirationDate", expression = "java(java.time.Instant.now().plus(dto.days(), java.time.temporal.ChronoUnit.DAYS))")
    PurchaseRequest toPurchase(RequestCreateEditDto dto);

    void map(@MappingTarget PurchaseRequest to, RequestCreateEditDto from);
}
