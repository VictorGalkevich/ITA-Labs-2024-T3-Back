package com.ventionteams.applicationexchange.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD("USD"),
    EUR("EUR"),
    BYR("BYR"),
    RUB("RUB");

    private String name;
}
