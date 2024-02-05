package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
