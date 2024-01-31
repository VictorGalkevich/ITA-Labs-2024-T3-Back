package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Size;
import com.ventionteams.applicationexchange.entity.enumeration.Status;

public record LotUpdateDTO (
        String title,
        @JsonProperty("category_id")
        Integer categoryId,
        @JsonProperty("subcategory_id")
        Integer subcategoryId,
        Long quantity,
        @JsonProperty("price_per_unit")
        Double pricePerUnit,
        Location location,
        String description,
        Status status,
        @JsonProperty("image_url")
        String imageUrl,
        String variety,
        Size size,
        Packaging packaging) {
}
