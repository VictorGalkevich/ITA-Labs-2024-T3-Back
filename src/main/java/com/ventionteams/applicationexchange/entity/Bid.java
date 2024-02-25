package com.ventionteams.applicationexchange.entity;


import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import jakarta.persistence.*;
import lombok.*;

import static lombok.EqualsAndHashCode.*;

@Data
@Builder
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "bids")
public class Bid extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "lot_id", nullable = false)
    private Lot lot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private BidStatus status;

    @Column(nullable = false, name = "amount")
    private Long amount;

    @Column(nullable = false, name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
