package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;

public record BidUserReadDto (
        @JsonProperty("bid_id")
        Long id,
        @JsonProperty("lot_id")
        Long lotId,
        BidStatus status,
        Long amount,
        Currency currency
) {
}
