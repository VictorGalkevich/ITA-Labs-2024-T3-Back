package com.ventionteams.applicationexchange.config;

import com.ventionteams.applicationexchange.client.RatesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ClientConfig {
    private final LinkConfig linkConfig;
    @Bean
    public RatesClient ratesClient() {
        return new RatesClient(linkConfig.getRatesApi());
    }
}
