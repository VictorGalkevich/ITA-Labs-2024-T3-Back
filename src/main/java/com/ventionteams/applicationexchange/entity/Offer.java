package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.OfferStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.EqualsAndHashCode.Include;

@Data
@Builder
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, name = "lot_id")
    private Lot lot;

    @Column(nullable = false, name = "purchase_request_id")
    private Long purchaseRequestId;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
}
