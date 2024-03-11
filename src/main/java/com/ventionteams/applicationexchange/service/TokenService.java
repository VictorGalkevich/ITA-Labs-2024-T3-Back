package com.ventionteams.applicationexchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.mapper.UserMapper;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final ObjectMapper mapper;
    private final UserMapper userMapper;
    private final UserRepository repository;
    public UserReadDto parseToken(String token) throws JsonProcessingException {
        final String payload = token.split("\\.")[1];
        final byte[] payloadBytes =  Base64.getUrlDecoder().decode(payload);
        final String payloadString = new String(payloadBytes, StandardCharsets.UTF_8);
        UserReadDto dto = mapper.readValue(payloadString, UserReadDto.class);
        dto = userMapper.toUserReadDto(repository.findByEmail(dto.email()).orElseThrow());
        return dto;
    }
}
