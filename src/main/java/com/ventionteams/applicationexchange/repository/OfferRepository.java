package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
