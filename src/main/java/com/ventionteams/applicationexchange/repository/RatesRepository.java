package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatesRepository extends JpaRepository<ExchangeRate, Long> {
    ExchangeRate findFirstBy();
}
