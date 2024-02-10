package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE("active"),
    COMPLETED("completed"),
    MODERATED("moderated");

    private String name;
}
