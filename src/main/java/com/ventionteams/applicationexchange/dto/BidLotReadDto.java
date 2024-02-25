package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;

public record BidLotReadDto(
        @JsonProperty("bid_id")
        Long id,
        @JsonProperty("author_id")
        Long authorId,
        Long amount,
        Currency currency
) {
}
