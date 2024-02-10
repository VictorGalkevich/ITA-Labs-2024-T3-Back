package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    EXCHANGE_EMPLOYEE("exchange employee"),
    SYSTEM_ADMINISTRATOR("system administrator"),
    REGISTERED_USER("registered user");

    private String name;
}
