package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;

public record OfferCreateEditDto(
        @JsonProperty("lot_id")
        @Min(1)
        Long lotId,
        @JsonProperty("purchase_request_id")
        @Min(1)
        Long purchaseRequestId
) {
}
