package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    @JsonProperty("employee")
    EMPLOYEE,
    @JsonProperty("administrator")
    ADMINISTRATOR,
    @JsonProperty("user")
    USER;
    @Override
    public String getAuthority() {
        return name();
    }
}
