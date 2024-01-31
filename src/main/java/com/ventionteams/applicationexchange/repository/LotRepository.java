package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Integer> {
}
