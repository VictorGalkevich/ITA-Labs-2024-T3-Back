package com.ventionteams.applicationexchange.scheduler;

import com.ventionteams.applicationexchange.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class LotScheduler {
    private final LotRepository lotRepository;

    @Scheduled(fixedDelayString = "PT${app.scheduler.interval}")
    @Transactional
    public void updateStatuses() {
        int count = lotRepository.updateExpiredLots(Instant.now());
        log.trace("Updated statuses of %d lots".formatted(count));
    }
}
