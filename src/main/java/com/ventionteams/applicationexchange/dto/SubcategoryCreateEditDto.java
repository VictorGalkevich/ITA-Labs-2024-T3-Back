package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubcategoryCreateEditDto {
    @JsonProperty("subcategory_id")
    private Integer id;
    @JsonProperty("category_id")
    private Integer categoryId;
    private String name;
}
