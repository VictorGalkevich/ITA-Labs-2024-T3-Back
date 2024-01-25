package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.ventionteams.applicationexchange.entity.Subcategory;
import lombok.Value;

import java.util.List;

@Value
public class CategoryReadDto {
    @JsonProperty("category_id")
    Integer id;
    String name;
    List<SubcategoryReadDto> subcategories;
}
