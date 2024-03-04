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
    @JsonProperty("crate")
    CRATE,
    @JsonProperty("Bottle")
    BOTTLE,
    @JsonProperty("Bunch")
    BUNCH,
    @JsonProperty("Sack")
    SACK
}
