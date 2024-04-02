package com.ventionteams.applicationexchange.scheduler;

import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class LotAndRequestScheduler {
    private final LotRepository lotRepository;
    private final RequestRepository requestRepository;

    @Scheduled(fixedDelayString = "PT${app.lot-scheduler.interval}")
    @Transactional
    public void updateStatuses() {
        int countLot = lotRepository.updateExpiredLots(Instant.now());
        int countRequest = requestRepository.updateExpiredLots(Instant.now());
        log.trace("Updated statuses of %d lots".formatted(countLot));
        log.trace("Updated statuses of %d requests".formatted(countRequest));
    }
}
