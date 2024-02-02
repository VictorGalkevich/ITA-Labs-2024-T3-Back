package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Size;
import com.ventionteams.applicationexchange.entity.enumeration.Status;

import java.time.Instant;

public record LotReadDTO (
    @JsonProperty("lot_id")
    Integer id,
    String title,
    @JsonProperty("category_id")
    Integer categoryId,
    @JsonProperty("category_name")
    String category,
    @JsonProperty("subcategory_id")
    Integer subcategoryId,
    @JsonProperty("subcategory_name")
    String subcategory,
    Long quantity,
    @JsonProperty("price_per_unit")
    Double pricePerUnit,
    Location location,
    String description,
    Status status,
    @JsonProperty("image_url")
    String imageUrl,
    @JsonProperty("expiration_date")
    Instant expirationDate,
    String variety,
    Size size,
    Packaging packaging,
    @JsonProperty("created_at")
    Instant createdAt) {

}