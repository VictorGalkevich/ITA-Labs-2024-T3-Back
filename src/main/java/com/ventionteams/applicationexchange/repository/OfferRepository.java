package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Offer;
import com.ventionteams.applicationexchange.entity.enumeration.OfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Page<Offer> findAllByPurchaseRequestIdAndStatusIn(Long id, List<OfferStatus> statuses, PageRequest req);

    @Query("SELECT l.user.id FROM Offer o JOIN o.lot l ON o.lot.id = l.id")
    Page<Offer> findByUserId(UUID id, PageRequest request);
}
