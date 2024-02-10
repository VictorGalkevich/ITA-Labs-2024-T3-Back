package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size {
    LARGE("Large"),
    SMALL("Small"),
    MEDIUM("Medium");

    private String name;
}
