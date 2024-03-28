package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.create.BidCreateDto;
import com.ventionteams.applicationexchange.dto.read.BidReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface BidMapper {

    BidReadDto toReadDto(Bid bid);

    @Mapping(target = "status", expression = "java(BidStatus.LEADING)")
    Bid toBid(BidCreateDto dto);
}
