package com.ventionteams.applicationexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Subcategory {
    private Integer id;
    private Integer categoryId;
    private String name;
}
