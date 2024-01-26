package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public record CategoryReadDto(
        @JsonProperty("category_id") Integer id,
        String name,
        List<SubcategoryReadDto> subcategories) {
}
