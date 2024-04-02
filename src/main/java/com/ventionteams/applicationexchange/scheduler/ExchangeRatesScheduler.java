package com.ventionteams.applicationexchange.scheduler;

import com.ventionteams.applicationexchange.service.RatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangeRatesScheduler {
    private final RatesService ratesService;

    @Scheduled(fixedDelayString = "PT${app.rate-scheduler.interval}")
    @Transactional
    public void updateRates() {
        log.trace("Updating exchange rates");
        ratesService.update();
    }
}
