package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LotUpdateDTO(
        @NotBlank
        String title,
        @JsonProperty("category_id")
        Integer categoryId,
        @NotNull
        @Min(1)
        Long quantity,
        @NotNull
        Weight weight,
        @JsonProperty("total_price")
        @Min(0)
        Double totalPrice,
        @NotNull
        LocationCreateDto location,
        @NotBlank
        String description,
        @JsonProperty("start_price")
        @Min(0)
        Double startPrice,
        @NotNull
        @Min(0)
        Integer fromSize,
        @NotNull
        @Min(0)
        Integer toSize,
        @JsonProperty("expiration_days")
        Integer days,
        @NotNull
        Packaging packaging,
        @JsonProperty("length_unit")
        LengthUnit lengthUnit,
        @NotNull
        Currency currency) {
}
