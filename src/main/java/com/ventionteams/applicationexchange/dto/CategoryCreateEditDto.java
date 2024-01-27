package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public record CategoryCreateEditDto(
        @JsonProperty("category_id") Integer id,
        String name,
        List<SubcategoryCreateEditDto> subcategories) {
}
