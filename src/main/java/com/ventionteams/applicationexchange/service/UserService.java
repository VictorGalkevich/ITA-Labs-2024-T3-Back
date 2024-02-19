package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.mapper.UserMapper;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public Page<UserReadDto> findAll(Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return userRepository.findAll(req)
                .map(userMapper::toUserReadDto);
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserReadDto);
    }

    public UserReadDto create(UserCreateEditDto dto) {
        return Optional.of(dto)
                .map(userMapper::toUser)
                .map(userRepository::save)
                .map(userMapper::toUserReadDto)
                .orElseThrow();
    }

    public Optional<UserReadDto> update(Long id, UserCreateEditDto dto) {
        return userRepository.findById(id)
                .map(user -> userMapper.toUser(dto))
                .map(userRepository::saveAndFlush)
                .map(userMapper::toUserReadDto);
    }

    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(category -> {
                    userRepository.delete(category);
                    return true;
                })
                .orElse(false);
    }
}
