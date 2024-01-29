package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("exchange employee")
    EXCHANGE_EMPLOYEE,
    @JsonProperty("system administrator")
    SYSTEM_ADMINISTRATOR,
    @JsonProperty("registered user buyer")
    REGISTERED_USER_BUYER,
    @JsonProperty("registered user seller")
    REGISTERED_USER_SELLER,
    @JsonProperty("guest")
    GUEST
}
