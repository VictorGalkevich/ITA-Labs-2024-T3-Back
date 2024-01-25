package com.ventionteams.applicationexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryReadDto {
    @JsonProperty("category_id")
    private Integer id;
    private String name;
    private List<SubcategoryReadDto> subcategories;
}
