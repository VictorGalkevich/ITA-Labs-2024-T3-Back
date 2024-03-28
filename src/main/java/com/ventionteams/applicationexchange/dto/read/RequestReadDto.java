package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;

import java.util.UUID;

public record RequestReadDto(
        @JsonProperty("request_id")
        Long id,
        @JsonProperty("category_id")
        Integer categoryId,
        @JsonProperty("user_id")
        UUID userId,
        LotStatus status,
        @JsonProperty("desired_price")
        Long desiredPrice,

        String description,

        Currency currency
) {
}
