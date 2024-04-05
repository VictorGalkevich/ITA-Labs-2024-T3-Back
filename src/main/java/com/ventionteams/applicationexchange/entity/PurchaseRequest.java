package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

import static lombok.EqualsAndHashCode.Include;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "requests")
public class PurchaseRequest implements UserMappedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LotStatus status;

    @Column(name = "desired_price", nullable = false)
    private Double desiredPrice;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "description")
    private String description;

    @Column(name = "reject_message")
    private String rejectMessage;

    @Column(name = "offer_quantity")
    private Integer offerQuantity;

    @Column(nullable = false, name = "expiration_date")
    private Instant expirationDate;

    @Column(nullable = false, name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "weight")
    private Weight weight;

    @Column(nullable = false, name = "price_per_unit")
    private Double pricePerUnit;

    @Embedded
    private Location location;

    @Column(nullable = false, name = "size")
    private Integer size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "packaging")
    private Packaging packaging;

    @Column(nullable = false, name = "length_unit")
    @Enumerated(EnumType.STRING)
    private LengthUnit lengthUnit;
}
