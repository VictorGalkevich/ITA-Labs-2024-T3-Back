package com.ventionteams.applicationexchange.dto;

import com.ventionteams.applicationexchange.entity.enumeration.Role;

import java.util.List;
import java.util.UUID;

public record UserAuthDto(
        UUID id,
        String email,
        List<Role> authorities
) {
}
