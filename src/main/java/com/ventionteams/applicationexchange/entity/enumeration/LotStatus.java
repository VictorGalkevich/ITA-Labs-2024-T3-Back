package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;


public enum LotStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("sold")
    SOLD,
    @JsonProperty("moderated")
    MODERATED
}
