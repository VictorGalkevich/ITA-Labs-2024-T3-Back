package com.ventionteams.applicationexchange.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventionteams.applicationexchange.container.JsonContainer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class RepositoryConfiguration {
    private final ObjectMapper objectMapper;
    @SneakyThrows
    @Bean
    public JsonContainer jsonContainer() {
        File file = new File("mock-entities.json");
        return objectMapper.readValue(file, JsonContainer.class);
    }
}
