package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OfferReadDto(
        @JsonProperty("offer_id")
        Long id,
        LotReadDTO lot,
        @JsonProperty("purchase_request_id")
        Long purchaseRequestId
) {
}
