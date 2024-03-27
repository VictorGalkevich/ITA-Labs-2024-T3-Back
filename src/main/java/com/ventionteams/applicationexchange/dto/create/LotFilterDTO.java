package com.ventionteams.applicationexchange.dto.create;

import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;

import java.util.List;

public record LotFilterDTO(
        Integer category,
        List<Packaging> packaging,
        List<Integer> locations,
        List<Integer> varieties,
        List<Weight> weights,
        Long fromQuantity,
        Long toQuantity,
        Integer fromSize,
        Integer toSize,
        Integer fromPrice,
        Integer toPrice,
        LotStatus lotStatus
) {
}
