package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Lot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LotRepository extends
        JpaRepository<Lot, Long>,
        JpaSpecificationExecutor<Lot>,
        LotUpdateRepository
{
    Page<Lot> findAllByCategoryId(Integer id, Pageable pageable);
}
