package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Offer;
import com.ventionteams.applicationexchange.entity.enumeration.OfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Page<Offer> findAllByPurchaseRequestIdAndStatusIn(Long id, List<OfferStatus> statuses, PageRequest req);
}
