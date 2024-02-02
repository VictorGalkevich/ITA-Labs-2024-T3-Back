package com.ventionteams.applicationexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "lots")
@Builder
@Entity
@Table(name = "locations")
public class Location{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Integer id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String region;
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    @Builder.Default
    private List<Lot> lots = new ArrayList<>();
}
