package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.List;

@Data
public class CategoryCreateEditDto {
    @JsonProperty("category_id")
    private Integer id;
    private String name;
    private List<SubcategoryCreateEditDto> subcategories;
}
