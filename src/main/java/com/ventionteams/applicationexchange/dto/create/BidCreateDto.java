package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class BidCreateDto {
    @JsonIgnore
    private UUID userId;
    @JsonProperty("lot_id")
    private Long lotId;
    private Double amount;
    private Currency currency;
}
