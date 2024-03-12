package com.ventionteams.applicationexchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventionteams.applicationexchange.dto.UserAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final ObjectMapper mapper;

    public UserAuthDto parseToken(String token) throws JsonProcessingException {
        final String payload = token.split("\\.")[1];
        final byte[] payloadBytes = Base64.getUrlDecoder().decode(payload);
        final String payloadString = new String(payloadBytes, StandardCharsets.UTF_8);
        return mapper.readValue(payloadString, UserAuthDto.class);
    }
}
