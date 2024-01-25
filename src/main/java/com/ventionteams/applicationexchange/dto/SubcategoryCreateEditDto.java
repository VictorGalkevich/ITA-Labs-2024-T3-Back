package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class SubcategoryCreateEditDto {
    @JsonProperty("subcategory_id")
    Integer id;
    @JsonProperty("category_id")
    Integer categoryId;
    String name;
}
