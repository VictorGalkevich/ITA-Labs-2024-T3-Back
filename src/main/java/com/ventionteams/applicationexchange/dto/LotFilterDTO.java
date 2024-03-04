package com.ventionteams.applicationexchange.dto;

import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;

import java.util.List;

public record LotFilterDTO(
        List<Integer> categories,
        List<Packaging> packaging,
        List<Integer> locations,
        List<String> varieties,
        List<Weight> weights,
        Long fromQuantity,
        Long toQuantity,
        Integer fromSize,
        Integer toSize
) {
}
