package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CategoryReadDto(
        @JsonProperty("category_id")
        Integer id,
        String name,
        @JsonProperty("parent_id")
        Integer parentId,
        List<CategoryReadDto> subcategories) {
}
