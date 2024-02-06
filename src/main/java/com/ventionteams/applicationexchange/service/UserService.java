package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.mapper.UserMapper;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserReadDto)
                .toList();
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
