package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("exchange employee")
    EXCHANGE_EMPLOYEE,
    @JsonProperty("system administrator")
    SYSTEM_ADMINISTRATOR,
    @JsonProperty("registered user")
    REGISTERED_USER,
}
