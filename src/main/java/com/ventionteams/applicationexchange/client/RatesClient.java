package com.ventionteams.applicationexchange.client;

import com.ventionteams.applicationexchange.dto.read.ExchangeRatesReadDto;

public class RatesClient extends Client{
    public RatesClient(String baseUrl) {
        super(baseUrl);
    }

    public ExchangeRatesReadDto retrieveExchangeRates() {
        return client().get()
                .uri("/latest/USD")
                .retrieve()
                .bodyToMono(ExchangeRatesReadDto.class)
                .block();
    }
}
