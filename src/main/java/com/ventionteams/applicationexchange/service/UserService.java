package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.read.UserReadDto;
import com.ventionteams.applicationexchange.mapper.UserMapper;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@TransactionalService
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

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
    public UserReadDto create(UserCreateEditDto dto, MultipartFile avatar) {
        return Optional.of(dto)
                .map(userMapper::toUser)
                .map(user -> {
                    user.setAvatarId(imageService.saveSingleImage(avatar, "avatar"));
                    user.setCreatedAt(Instant.now());
                    return user;
                })
                .map(userRepository::save)
                .map(userMapper::toUserReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(UUID id, UserCreateEditDto dto, MultipartFile newAvatar) {
        return userRepository.findById(id)
                .map(user -> {
                    imageService.deleteImage(user.getAvatarId());
                    user.setAvatarId(imageService.saveSingleImage(newAvatar, "avatar"));
                    userMapper.map(user, dto);
                    return user;
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::toUserReadDto);
    }

    @Transactional
    public boolean delete(UUID id) {
        return userRepository.findById(id)
                .map(user -> {
                    Long avatarId = user.getAvatarId();
                    user.setAvatarId(null);
                    imageService.deleteImage(avatarId);
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }
}
