package com.ventionteams.applicationexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "lots")
@Builder
@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String region;
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    @Builder.Default
    private List<Lot> lots = new ArrayList<>();

    public void addLot(Lot lot) {
        lots.add(lot);
        lot.setLocation(this);
    }
}
