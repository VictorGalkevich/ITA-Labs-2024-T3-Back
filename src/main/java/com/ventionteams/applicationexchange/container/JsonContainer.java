package com.ventionteams.applicationexchange.container;

import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.UserCreateEditDto;

import java.util.List;

public record JsonContainer(List<UserCreateEditDto> users,
                            List<CategoryCreateEditDto> categories,
                            List<LotReadDTO> lots) {
}