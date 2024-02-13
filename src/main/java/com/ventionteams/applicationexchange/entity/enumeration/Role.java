package com.ventionteams.applicationexchange.entity.enumeration;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements ResourceContainer {
    EXCHANGE_EMPLOYEE("exchange employee"),
    SYSTEM_ADMINISTRATOR("system administrator"),
    REGISTERED_USER("registered user");

    private String name;
}
