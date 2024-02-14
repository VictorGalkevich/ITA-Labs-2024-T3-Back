package com.ventionteams.applicationexchange.entity.enumeration;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Weight implements ResourceContainer {
    TON("ton"),
    KILOGRAM("kg"),
    PCS("PCS");

    private String name;
}
