package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.BidCreateDto;
import com.ventionteams.applicationexchange.dto.BidLotReadDto;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.dto.BidUserReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface BidMapper {
    @Mapping(target = "userId", expression = "java(bid.getUser().getId())")
    @Mapping(target = "lotId", expression = "java(bid.getLot().getId())")
    BidReadDto toReadDto(Bid bid);

    @Mapping(target = "authorId", expression = "java(bid.getUser().getId())")
    BidLotReadDto toLotReadDto(Bid bid);

    @Mapping(target = "lotId", expression = "java(bid.getLot().getId())")
    BidUserReadDto toUserReadDto(Bid bid);

    @Mapping(target = "user", expression = "java(com.ventionteams.applicationexchange.entity.User.builder().id(dto.userId()).build())")
    @Mapping(target = "lot", expression = "java(com.ventionteams.applicationexchange.entity.Lot.builder().id(dto.lotId()).build())")
    @Mapping(target = "status", expression = "java(BidStatus.LEADING)")
    Bid toBid(BidCreateDto dto);
}
