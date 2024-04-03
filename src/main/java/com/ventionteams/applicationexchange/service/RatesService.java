package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.read.ExchangeRatesReadDto.ConversionRates;
import com.ventionteams.applicationexchange.entity.ExchangeRate;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.repository.RatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ventionteams.applicationexchange.entity.enumeration.Currency.BYN;

@Service
@RequiredArgsConstructor
public class RatesService {
    private final RatesRepository ratesRepository;

    public void update(ConversionRates rates) {
        ExchangeRate rate = ratesRepository.findFirstBy();
        rate.setBuyByn(rates.buyByn());
        rate.setBuyEur(rates.buyEur());
        rate.setSellByn(1 / rates.buyByn());
        rate.setSellEur(1 / rates.buyEur());
        ratesRepository.saveAndFlush(rate);
    }
    
    public double convertFromUSD(Double amount, Currency toConvert) {
        if (toConvert.equals(Currency.USD)) {
            return amount;
        }
        ExchangeRate rates = ratesRepository.findFirstBy();
        double exchangeRate = toConvert.equals(BYN) ? rates.getBuyByn() : rates.getBuyEur();
        return exchangeRate * amount;
    }

    public double convertToUSD(Double amount, Currency toConvert) {
        if (toConvert.equals(Currency.USD)) {
            return amount;
        }
        ExchangeRate rates = ratesRepository.findFirstBy();
        double exchangeRate = toConvert.equals(BYN) ? rates.getSellByn() : rates.getSellEur();
        return exchangeRate * amount;
    }
}
