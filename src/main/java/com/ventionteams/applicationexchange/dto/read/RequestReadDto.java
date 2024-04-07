package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;

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
        Currency currency,
        Long quantity,
        Weight weight,
        @JsonProperty("price_per_unit")
        Double pricePerUnit, Location location,
        Integer size,
        String title,
        @JsonProperty("category_name")
        String categoryName,
        Packaging packaging,
        @JsonProperty("length_unit")
        LengthUnit lengthUnit,
        @JsonProperty("reject_message")
        String rejectMessage
) {
}
