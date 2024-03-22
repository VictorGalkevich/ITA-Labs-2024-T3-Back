package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.entity.enumeration.*;

import java.util.List;

public record LotUpdateDTO(
        String title,
        @JsonProperty("category_id")
        Integer categoryId,
        Long quantity,
        Weight weight,
        @JsonProperty("total_price")
        Long totalPrice,
        LocationCreateDto location,
        String description,
        LotStatus status,
        @JsonProperty("start_price")
        Long startPrice,
        Integer size,
        @JsonProperty("expiration_days")
        Integer days,
        Packaging packaging,
        @JsonProperty("length_unit")
        LengthUnit lengthUnit,
        Currency currency) {
}
