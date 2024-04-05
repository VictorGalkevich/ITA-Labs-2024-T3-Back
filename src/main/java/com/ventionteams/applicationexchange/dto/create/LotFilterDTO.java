package com.ventionteams.applicationexchange.dto.create;

import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import lombok.Builder;

import java.util.List;

@Builder
public record LotFilterDTO(
        Integer category,
        List<Packaging> packaging,
        List<String> countries,
        List<String> cities,
        List<Integer> varieties,
        List<Weight> weights,
        Long fromQuantity,
        Long toQuantity,
        Integer fromSize,
        Integer toSize,
        Integer fromPrice,
        Integer toPrice,
        String lotStatus,
        String keyword
) {
}
