package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.UserMapper;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@TransactionalService
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserReadDto> findAll(Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return userRepository.findAll(req)
                .map(userMapper::toUserReadDto);
    }

    public Optional<UserReadDto> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toUserReadDto);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto dto, Image avatar) {
        return Optional.of(dto)
                .map(userMapper::toUser)
                .map(user -> {
                    user.setAvatarId(avatar.getId());
                    user.setCreatedAt(Instant.now());
                    return user;
                })
                .map(userRepository::save)
                .map(userMapper::toUserReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(UUID id, UserCreateEditDto dto) {
        return userRepository.findById(id)
                .map(user -> {
                    userMapper.map(user, dto);
                    return user;
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::toUserReadDto);
    }

    @Transactional
    public boolean delete(UUID id) {
        return userRepository.findById(id)
                .map(category -> {
                    userRepository.delete(category);
                    return true;
                })
                .orElse(false);
    }
}
