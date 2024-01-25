package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Value;

import java.util.List;

@Value
public class CategoryCreateEditDto {
    @JsonProperty("category_id")
    Integer id;
    String name;
    List<SubcategoryCreateEditDto> subcategories;
}
