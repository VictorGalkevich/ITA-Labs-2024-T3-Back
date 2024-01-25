package com.ventionteams.applicationexchange.container;

import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;

import java.util.List;

public record CategoryContainer(List<CategoryReadDto> categories) {
}
