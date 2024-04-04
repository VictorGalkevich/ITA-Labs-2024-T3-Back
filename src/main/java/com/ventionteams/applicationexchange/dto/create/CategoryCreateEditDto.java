package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record CategoryCreateEditDto(
        @NotEmpty
        String name,
        @JsonProperty("parent_id")
        Integer parentId) {
}
