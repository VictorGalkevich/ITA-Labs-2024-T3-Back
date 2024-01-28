package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Status;

public record LotReadDTO (
    @JsonProperty("lot_id") Integer id,
    String title,
    @JsonProperty("product_type") String category,
    @JsonProperty("product_subtype") String subcategory,
    Long quantity,
    @JsonProperty("price_per_unit") Double pricePerUnit,
    Location location,
    String description,
    Status status,
    @JsonProperty("image_url") String imageUrl) {

}