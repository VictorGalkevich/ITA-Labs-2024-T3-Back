package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Role;

import java.util.List;

public record UserReadDto (

        @JsonProperty("user_id")
        Long id,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String email,
        Role role,
        String phoneNumber,
        List<BidUserReadDto> bids) {
}
