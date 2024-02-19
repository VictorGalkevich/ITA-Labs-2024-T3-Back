package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Lot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Long> {
    Page<Lot> findAllByCategoryId(Integer id, Pageable pageable);
}
