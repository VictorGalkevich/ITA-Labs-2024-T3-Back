package com.ventionteams.applicationexchange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Lot {
    @JsonProperty("lot_id")
    private Integer id;
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
