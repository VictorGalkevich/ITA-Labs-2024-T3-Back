package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.Role;

import java.util.List;
import java.util.UUID;

public record UserReadDto(

        @JsonProperty("user_id")
        UUID id,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String email,
        Role role,
        String phoneNumber,
        @JsonProperty("preferred_currency")
        Currency currency,
        List<BidReadDto> bids) {
}
