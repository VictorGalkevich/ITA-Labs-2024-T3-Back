package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;

import java.time.Instant;

public record BidForUserDto(
        @JsonProperty("bid_id")
        Long id,
        @JsonProperty("lot_id")
        Long lotId,
        BidStatus status,
        Long amount,
        Currency currency,
        String title,
        @JsonProperty("expiration_date")
        Instant expirationDate,
        @JsonProperty("total_price")
        Double totalPrice,
        @JsonProperty("price_per_unit")
        Double pricePerUnit,
        Weight weight
) {
}
