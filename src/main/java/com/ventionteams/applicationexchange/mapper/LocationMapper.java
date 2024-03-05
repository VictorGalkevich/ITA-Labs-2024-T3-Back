package com.ventionteams.applicationexchange.mapper;


import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.LocationCreateDto;
import com.ventionteams.applicationexchange.entity.Location;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class, uses = BidMapper.class)
public interface LocationMapper {
    Location toLocation(LocationCreateDto dto);
}
