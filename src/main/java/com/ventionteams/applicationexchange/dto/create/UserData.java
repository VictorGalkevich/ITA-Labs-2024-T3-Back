package com.ventionteams.applicationexchange.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record UserData(
        @JsonProperty("first_name")
        @NotBlank
        String firstName,
        @JsonProperty("last_name")
        @NotBlank
        String lastName,
        @Pattern(regexp = "[+]375\\d{9}")
        String phoneNumber,
        @JsonProperty("preferred_currency")
        Currency currency
) {
}
