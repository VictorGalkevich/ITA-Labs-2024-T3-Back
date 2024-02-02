package com.ventionteams.applicationexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.EqualsAndHashCode.*;

@Data
@Builder
@NoArgsConstructor
@ToString(exclude = "lots")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    @Builder.Default
    private List<Lot> lots = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
}
