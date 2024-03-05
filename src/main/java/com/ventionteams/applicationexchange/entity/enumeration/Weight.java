package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Weight {
    @JsonProperty("ton")
    TON,
    @JsonProperty("kg")
    KILOGRAM,
    @JsonProperty("PCS")
    PCS
}
