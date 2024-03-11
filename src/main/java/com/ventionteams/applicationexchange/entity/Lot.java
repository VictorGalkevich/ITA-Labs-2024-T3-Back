package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "weight")
    private Weight weight;

    @Column(nullable = false, name = "price_per_unit")
    private Double pricePerUnit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @Column(nullable = false, name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private LotStatus status;
  
    @OneToMany(mappedBy = "lot")
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
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "currency")
    private Currency currency;
}
