package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Weight {
    TON("ton"),
    KILOGRAM("kg");

    private String name;
}
