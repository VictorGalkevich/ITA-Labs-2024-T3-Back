package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;

import java.util.UUID;

public record BidReadDto(
        @JsonProperty("bid_id")
        Long id,
        @JsonProperty("user_id")
        UUID userId,
        @JsonProperty("lot_id")
        Long lotId,
        BidStatus status,
        Long amount,
        Currency currency
) {

}
