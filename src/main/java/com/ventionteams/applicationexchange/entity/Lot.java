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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "lots")
public class Lot extends AuditingEntity implements UserMappedEntity {
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

    @Column(nullable = false, name = "from_size")
    private Integer fromSize;

    @Column(nullable = false, name = "to_size")
    private Integer toSize;

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

    @Column(nullable = false, name = "start_price")
    private Double startPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reject_message")
    private String rejectMessage;

    @Column(name = "buyer_id")
    private UUID buyerId;
}
