package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OfferCreateEditDto(
        @JsonProperty("lot_id")
        @NotNull
        @Min(1)
        Long lotId,
        @JsonProperty("purchase_request_id")
        @NotNull
        @Min(1)
        Long purchaseRequestId
) {
}
