package com.ventionteams.applicationexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@ToString(exclude = {"subcategories", "lots"})
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subcategory> subcategories;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    @Builder.Default
    private List<Lot> lots = new ArrayList<>();

    public void addSubcategory(Subcategory subcategory) {
        subcategories.add(subcategory);
        subcategory.setCategory(this);
    }

    public void addLot(Lot lot) {
        lots.add(lot);
        lot.setCategory(this);
    }
}
