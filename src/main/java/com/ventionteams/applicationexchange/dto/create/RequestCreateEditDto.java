package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestCreateEditDto(
        @JsonProperty("category_id")
        @Min(1)
        @NotNull
        Integer categoryId,

        @JsonProperty("desired_price")
        @Min(0)
        @NotNull
        Long desiredPrice,
        String description,

        @NotNull
        Currency currency,

        @JsonProperty("expiration_days")
        @NotNull
        @Min(1)
        Integer days
) {
}
