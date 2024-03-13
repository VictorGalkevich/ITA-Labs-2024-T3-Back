package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Page<Bid> findAllByUserId(UUID id, PageRequest req);

    Optional<Bid> findByLotIdAndStatus(Long id, BidStatus status);

    Optional<Bid> findByUserIdAndLotId(UUID userId, Long lotId);
}
