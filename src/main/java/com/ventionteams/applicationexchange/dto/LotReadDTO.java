package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Size;
import com.ventionteams.applicationexchange.entity.enumeration.Status;

import java.time.Instant;
import java.util.List;

public record LotReadDTO (
    @JsonProperty("lot_id")
    Long id,
    String title,
    @JsonProperty("category_id")
    Integer categoryId,
    @JsonProperty("category_name")
    String category,
    Long quantity,
    @JsonProperty("price_per_unit")
    Double pricePerUnit,
    Location location,
    String description,
    Status status,
    @JsonProperty("image_url")
    List<Image> images,
    @JsonProperty("expiration_date")
    Instant expirationDate,
    String variety,
    Integer size,
    Packaging packaging,
    @JsonProperty("created_at")
    Instant createdAt) {

}