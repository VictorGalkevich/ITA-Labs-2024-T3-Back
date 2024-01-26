package com.ventionteams.applicationexchange.entity;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(exclude = {"subcategories"})
@EqualsAndHashCode
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private List<Subcategory> subcategories;
}
