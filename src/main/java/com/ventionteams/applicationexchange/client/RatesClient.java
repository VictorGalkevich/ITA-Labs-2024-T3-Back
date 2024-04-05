package com.ventionteams.applicationexchange.client;

import com.ventionteams.applicationexchange.dto.read.ExchangeRatesReadDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ratesClient", url = "#{@linkConfig.getRatesApi()}")
public interface RatesClient {

    @GetMapping("/latest/USD")
    ExchangeRatesReadDto retrieveExchangeRates();
}
