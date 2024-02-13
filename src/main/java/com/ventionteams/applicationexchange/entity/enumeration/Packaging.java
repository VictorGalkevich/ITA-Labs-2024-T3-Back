package com.ventionteams.applicationexchange.entity.enumeration;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Packaging implements ResourceContainer {
    BOX("Box"),
    BASKET("Basket"),
    CARTON("Carton"),
    BAG("Bag"),
    CRATE("Crate"),
    BOTTLE("Bottle"),
    BUNCH("Bunch"),
    SACK("Sack");

    private String name;
}
