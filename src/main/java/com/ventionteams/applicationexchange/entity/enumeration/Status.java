package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.Getter;

@Getter
public enum Status {
    active("active"),
    completed("completed"),
    moderated("moderated");

    private final String name;

    Status(String name) {
        this.name = name;
    }
}
