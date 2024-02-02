package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findAllByCategoryId(Integer id);
}
