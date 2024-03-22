package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BidRepository extends JpaRepository<Bid, Long>, UserBidRepository {
    Optional<Bid> findByLotIdAndStatus(Long id, BidStatus status);

    Optional<Bid> findByUserIdAndLotId(UUID userId, Long lotId);
}
