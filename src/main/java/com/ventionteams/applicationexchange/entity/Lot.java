package com.ventionteams.applicationexchange.entity;

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
    private Integer id;
    private String title;
    private String category;
    private String subcategory;
    private Long quantity;
    private Double pricePerUnit;
    private Location location;
    private String description;
    private Status status;
    private String imageUrl;
}
