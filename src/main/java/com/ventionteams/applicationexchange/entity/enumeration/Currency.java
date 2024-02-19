package com.ventionteams.applicationexchange.entity.enumeration;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency implements ResourceContainer {
    USD("USD"),
    EUR("EUR"),
    BYN("BYN");

    private String name;
}
