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
public class Lot extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Double pricePerUnit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String variety;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Packaging packaging;

    @Column(nullable = false)
    private Instant expirationDate;
}
