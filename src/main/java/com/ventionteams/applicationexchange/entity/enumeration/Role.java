package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    @JsonProperty("exchange employee")
    EMPLOYEE,
    @JsonProperty("system administrator")
    SYSTEM_ADMINISTRATOR,
    @JsonProperty("registered user")
    REGISTERED_USER,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
