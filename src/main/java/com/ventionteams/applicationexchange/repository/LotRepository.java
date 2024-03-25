package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LotRepository extends JpaRepository<Lot, Long>, JpaSpecificationExecutor<Lot> {
    Page<Lot> findAllByCategoryId(Integer id, Pageable pageable);
    Page<Lot> findByStatus(LotStatus status, Pageable pageable);
    Page<Lot> findByStatusAndUserId(LotStatus lotStatus, UUID user_id, Pageable pageable);
}
