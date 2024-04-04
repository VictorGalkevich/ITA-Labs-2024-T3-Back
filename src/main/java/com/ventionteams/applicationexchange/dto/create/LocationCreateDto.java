package com.ventionteams.applicationexchange.dto.create;

import jakarta.validation.constraints.NotEmpty;

public record LocationCreateDto(
        @NotEmpty
        String country,
        @NotEmpty
        String region
) {
}
