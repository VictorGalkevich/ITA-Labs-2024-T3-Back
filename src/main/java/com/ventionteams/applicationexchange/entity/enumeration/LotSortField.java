package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LotSortField {
    QUANTITY("quantity"),
    CREATED_AT("createdAt"),
    EXPIRATION_DATE("expirationDate"),
    SIZE("size");

    private String name;
}