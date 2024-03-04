package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.BidCreateDto;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface BidMapper {

    BidReadDto toReadDto(Bid bid);

    @Mapping(target = "status", expression = "java(BidStatus.LEADING)")
    Bid toBid(BidCreateDto dto);
}
