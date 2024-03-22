package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Column(nullable = false, name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "weight")
    private Weight weight;

    @Column(nullable = false, name = "price_per_unit")
    private Double pricePerUnit;

    @Embedded
    private Location location;

    @Column(nullable = false, name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private LotStatus status;
  
    @OneToMany(mappedBy = "lot", fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    @Column(nullable = false, name = "size")
    private Integer size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "packaging")
    private Packaging packaging;

    @Column(nullable = false, name = "expiration_date")
    private Instant expirationDate;

    @Column(nullable = false, name = "length_unit")
    @Enumerated(EnumType.STRING)
    private LengthUnit lengthUnit;

    @Column(nullable = false, name = "bid_quantity")
    private Integer bidQuantity;

    @Column(nullable = false, name = "total_price")
    private Long totalPrice;

    @Column(nullable = false, name = "start_price")
    private Long startPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "currency")
    private Currency currency;

    @Column(nullable = false, name = "user_id")
    private UUID userId;
}
