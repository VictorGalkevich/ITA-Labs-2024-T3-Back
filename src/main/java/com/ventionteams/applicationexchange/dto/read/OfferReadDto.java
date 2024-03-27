package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Lot;

public record OfferReadDto(
        @JsonProperty("offer_id")
        Long id,
        LotReadDTO lot,
        @JsonProperty("purchase_request_id")
        Long purchaseRequestId
) {
}
