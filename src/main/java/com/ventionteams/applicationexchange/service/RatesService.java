package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.client.RatesClient;
import com.ventionteams.applicationexchange.dto.read.ExchangeRatesReadDto.ConversionRates;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.repository.RatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RatesService {
    private final RatesClient client;
    private final RatesRepository ratesRepository;
    @Transactional
    public void update() {
        ConversionRates rates = client.retrieveExchangeRates().rates();
        ratesRepository.updateRates(
                rates.buyByn(),
                1 / rates.buyByn(),
                rates.buyEur(),
                1 / rates.buyEur()
        );
    }
    
    public double convertFromUSD(Double amount, Currency toConvert) {
        if (toConvert.equals(Currency.USD)) {
            return amount;
        }
        Map<String, Object> rates = ratesRepository.getRates();
        String name = toConvert.name().toLowerCase();
        double exchangeRate = (double) rates.get("buy_%s".formatted(name));
        return exchangeRate * amount;
    }

    public double convertToUSD(Double amount, Currency toConvert) {
        if (toConvert.equals(Currency.USD)) {
            return amount;
        }
        Map<String, Object> rates = ratesRepository.getRates();
        String name = toConvert.name().toLowerCase();
        double exchangeRate = (double) rates.get("sell_%s".formatted(name));
        return exchangeRate * amount;
    }
}
