package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;

public record LotUpdateDTO(
        String title,
        @JsonProperty("category_id")
        Integer categoryId,
        Long quantity,
        Weight weight,
        @JsonProperty("price_per_unit")
        Double pricePerUnit,
        LocationCreateDto location,
        String description,
        LotStatus status,
//        @JsonProperty("image_url")
//        String imageUrl,
        String variety,
        Integer size,
        @JsonProperty("expiration_days")
        Integer days,
        Packaging packaging,
        @JsonProperty("length_unit")
        LengthUnit lengthUnit) {
}
