package com.ventionteams.applicationexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "lots")
@AllArgsConstructor
@Entity
@Table(name = "subcategories")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String name;
    @OneToMany(mappedBy = "subcategory")
    @JsonIgnore
    @Builder.Default
    private List<Lot> lots = new ArrayList<>();

    public void addLot(Lot lot) {
        lots.add(lot);
        lot.setSubcategory(this);
    }
}
