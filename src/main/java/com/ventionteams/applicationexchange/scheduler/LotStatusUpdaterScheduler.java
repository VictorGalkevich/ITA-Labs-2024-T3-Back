package com.ventionteams.applicationexchange.scheduler;

import com.ventionteams.applicationexchange.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class LotStatusUpdaterScheduler {
    private final LotRepository lotRepository;
    @Scheduled(fixedDelayString = "PT${app.scheduler.interval}")
    @Transactional
    public void update() {
        int count = lotRepository.updateExpiredLots();
        log.trace("Updated statuses of %d lots".formatted(count));
    }
}
