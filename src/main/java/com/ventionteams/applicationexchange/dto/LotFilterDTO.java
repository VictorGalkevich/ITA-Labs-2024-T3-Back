package com.ventionteams.applicationexchange.dto;

import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public record LotFilterDTO (
    List<Category> categories,
    List<Packaging> packaging,
    List<Location> locations,
    List<String> varieties,
    List<Weight> weights,
    Long fromQuantity,
    Long toQuantity,
    Integer fromSize,
    Integer toSize
){}
