package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Size;
import com.ventionteams.applicationexchange.entity.enumeration.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@Entity
@Table(name = "lots")
public class Lot extends AuditingEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Integer id;
    private String title;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    private Long quantity;
    private Double pricePerUnit;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "location_id")
    private Location location;
    private String description;
    private Status status;
    private String imageUrl;
    private String variety;
    private Size size;
    private Packaging packaging;
    private Instant expirationDate;
}
