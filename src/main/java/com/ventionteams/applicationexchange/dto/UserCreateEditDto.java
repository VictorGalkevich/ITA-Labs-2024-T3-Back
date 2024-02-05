package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Role;

public record UserCreateEditDto(
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String email,
        Role role,
        String password) {
}