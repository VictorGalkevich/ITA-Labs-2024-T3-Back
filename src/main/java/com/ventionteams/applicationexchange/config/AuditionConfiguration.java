package com.ventionteams.applicationexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class AuditionConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        //user from security context
        return () -> Optional.of("admin");
    }
}
