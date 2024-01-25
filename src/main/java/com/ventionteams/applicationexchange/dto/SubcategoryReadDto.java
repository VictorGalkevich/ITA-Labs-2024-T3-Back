package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubcategoryReadDto {
    @JsonProperty("subcategory_id")
    private Integer id;
    @JsonProperty("category_id")
    private Integer categoryId;
    private String name;
}
