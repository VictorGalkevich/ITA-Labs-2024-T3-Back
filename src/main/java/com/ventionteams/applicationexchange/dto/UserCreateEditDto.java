package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserCreateEditDto(
        @JsonProperty("first_name")
        @NotBlank
        String firstName,
        @JsonProperty("last_name")
        @NotBlank
        String lastName,
        @Email
        String email,
        @NotNull
        Role role,
        @Pattern(regexp = "[+]375 [0-9]{2} [0-9]{3}-[0-9]{2}-[0-9]{2}")
        String phoneNumber,
        @JsonProperty("preferred_currency")
        Currency currency) {
}