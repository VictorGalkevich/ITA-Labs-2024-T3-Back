package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import jakarta.validation.constraints.Min;

public record LotUpdateDTO(
        String title,
        @JsonProperty("category_id")
        Integer categoryId,
        Long quantity,
        Weight weight,
        @JsonProperty("total_price")
        @Min(0)
        Long totalPrice,
        LocationCreateDto location,
        String description,
        @JsonProperty("start_price")
        @Min(0)
        Long startPrice,
        Integer size,
        @JsonProperty("expiration_days")
        Integer days,
        Packaging packaging,
        @JsonProperty("length_unit")
        LengthUnit lengthUnit,
        Currency currency) {
}
