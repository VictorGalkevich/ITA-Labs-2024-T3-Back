package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LengthUnit {
    @JsonProperty("cm")
    CENTIMETER,
    @JsonProperty("mm")
    MILLIMETER
}
