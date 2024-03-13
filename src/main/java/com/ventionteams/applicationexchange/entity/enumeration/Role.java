package com.ventionteams.applicationexchange.entity.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE,
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
