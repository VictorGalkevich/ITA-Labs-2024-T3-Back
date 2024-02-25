package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Page<Bid> findAllByUserId(Long id, PageRequest req);
}
