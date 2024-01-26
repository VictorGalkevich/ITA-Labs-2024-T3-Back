package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.model.Location;
import com.ventionteams.applicationexchange.model.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotUpdateDTO {
    private String title;
    @JsonProperty("product_type")
    private String category;
    @JsonProperty("product_subtype")
    private String subcategory;
    private Long quantity;
    @JsonProperty("price_per_unit")
    private Double pricePerUnit;
    private Location location;
    private String description;
    private Status status;
    @JsonProperty("image_url")
    private String imageUrl;
}
