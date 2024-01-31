package com.ventionteams.applicationexchange.dto;

import java.util.List;

public record CategoryCreateEditDto(
        String name,
        List<SubcategoryCreateEditDto> subcategories) {
}
