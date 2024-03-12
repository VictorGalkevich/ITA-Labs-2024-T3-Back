package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Role;

import java.util.List;
import java.util.UUID;

public record UserAuthDto(
        @JsonProperty("sub")
        UUID id,
        String email,
        @JsonProperty("cognito:groups")
        List<Role> authorities
) {
}
