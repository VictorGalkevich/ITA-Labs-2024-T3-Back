package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExchangeRatesReadDto(
        @JsonProperty("base_code")
        String baseCode,
        @JsonProperty("conversion_rates")
        ConversionRates rates

) {

    public record ConversionRates(
            @JsonProperty("BYN")
            Double buyByn,
            @JsonProperty("EUR")
            Double buyEur
    ) {
    }
}
