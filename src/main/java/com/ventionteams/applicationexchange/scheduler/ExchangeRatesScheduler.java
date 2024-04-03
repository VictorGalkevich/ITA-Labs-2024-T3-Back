package com.ventionteams.applicationexchange.scheduler;

import com.ventionteams.applicationexchange.client.RatesClient;
import com.ventionteams.applicationexchange.dto.read.ExchangeRatesReadDto;
import com.ventionteams.applicationexchange.service.RatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.ventionteams.applicationexchange.dto.read.ExchangeRatesReadDto.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangeRatesScheduler {
    private final RatesService ratesService;
    private final RatesClient client;

    @Scheduled(fixedDelayString = "PT${app.rate-scheduler.interval}")
    @Transactional
    public void updateRates() {
        log.trace("Updating exchange rates");
        ConversionRates rates = client.retrieveExchangeRates().rates();
        ratesService.update(rates);
    }
}
