package com.ventionteams.applicationexchange.container;

import com.ventionteams.applicationexchange.dto.CategoryReadDto;

import java.util.List;

public record CategoryContainer(List<CategoryReadDto> categories) {
}
