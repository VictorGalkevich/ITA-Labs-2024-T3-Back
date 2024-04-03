package com.ventionteams.applicationexchange.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.links")
@Getter
@Setter
@Component
public class LinkConfig {
    private String ratesApi = "https://v6.exchangerate-api.com/v6/07f68c2225ae9dd0c66bb44f";
}
