package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;

public record BidCreateDto(
        @JsonProperty("user_id")
        Long userId,
        @JsonProperty("lot_id")
        Long lotId,
        Long amount,
        Currency currency
) {

}
