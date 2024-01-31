package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubcategoryCreateEditDto(
        @JsonProperty("category_id")
        Integer categoryId,
        String name) {
}
