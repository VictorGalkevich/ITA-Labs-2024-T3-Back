package com.ventionteams.applicationexchange.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Packaging {
    @JsonProperty("Box")
    BOX,
    @JsonProperty("Basket")
    BASKET,
    @JsonProperty("Carton")
    CARTON,
    @JsonProperty("Bag")
    BAG,
    @JsonProperty("Crate")
    CRATE,
    @JsonProperty("Bottle")
    BOTTLE,
    @JsonProperty("Bunch")
    BUNCH,
    @JsonProperty("Sack")
    SACK
}
