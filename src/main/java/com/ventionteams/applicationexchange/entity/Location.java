package com.ventionteams.applicationexchange.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "locations")
public class Location{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Integer id;
    @Column(nullable = false, name = "country")
    private String country;
    @Column(nullable = false, name = "region")
    private String region;
}
