package com.ventionteams.applicationexchange.client;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class Client {
    private final WebClient client;

    protected Client(String baseUrl) {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public WebClient client() {
        return client;
    }
}