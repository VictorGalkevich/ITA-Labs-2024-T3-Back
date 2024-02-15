package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LotSortField {
    QUANTITY("quantity"),
    CREATED_AT("created_at"),
    EXPIRATION_DATE("expiration_date"),
    SIZE("size");

    private String name;
}
