package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.create.OfferCreateEditDto;
import com.ventionteams.applicationexchange.dto.read.OfferReadDto;
import com.ventionteams.applicationexchange.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class, uses = LotMapper.class)
public interface OfferMapper {

    OfferReadDto toReadDto(Offer offer);

    Offer toOffer(OfferCreateEditDto dto);
}
