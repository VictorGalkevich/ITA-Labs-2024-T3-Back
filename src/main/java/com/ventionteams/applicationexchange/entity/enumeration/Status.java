package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("completed")
    COMPLETED,
    @JsonProperty("moderated")
    MODERATED
}
