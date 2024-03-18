package com.ventionteams.applicationexchange.dto;

import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.Role;

import java.util.UUID;

public record UserCreateEditDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Role role,
        String phoneNumber,
        Currency currency) {
}