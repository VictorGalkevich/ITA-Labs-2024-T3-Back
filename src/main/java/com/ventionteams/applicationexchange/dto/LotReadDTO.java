package com.ventionteams.applicationexchange.dto;

import com.ventionteams.applicationexchange.model.Location;
import com.ventionteams.applicationexchange.model.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotReadDTO {
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