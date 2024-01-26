package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubcategoryReadDto(
        @JsonProperty("subcategory_id") Integer id,
        @JsonProperty("category_id") Integer categoryId,
        String name) {
}
