package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Page<Bid> findAllByUserId(Long id, PageRequest req);

    Optional<Bid> findByLotIdAndStatus(Long id, BidStatus status);

    Optional<Bid> findByUserIdAndLotId(Long userId, Long lotId);
}
