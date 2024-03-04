package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Currency {
    @JsonProperty("USD")
    USD,
    @JsonProperty("EUR")
    EUR,
    @JsonProperty("BYN")
    BYN
}
