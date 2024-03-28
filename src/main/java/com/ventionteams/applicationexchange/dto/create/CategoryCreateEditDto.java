package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryCreateEditDto(
        String name,
        @JsonProperty("parent_id")
        Integer parentId) {
}
