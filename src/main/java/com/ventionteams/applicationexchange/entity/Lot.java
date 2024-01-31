package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Size;
import com.ventionteams.applicationexchange.entity.enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "lots")
public class Lot extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
