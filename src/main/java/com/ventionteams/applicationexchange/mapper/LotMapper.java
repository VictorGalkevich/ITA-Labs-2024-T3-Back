package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfig;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface LotMapper {
    List<LotReadDTO> toLotReadList(List<Lot> lots);

    LotReadDTO toLotReadDTO(Lot lot);
    Lot toLot(LotReadDTO lotReadDTO);
}
