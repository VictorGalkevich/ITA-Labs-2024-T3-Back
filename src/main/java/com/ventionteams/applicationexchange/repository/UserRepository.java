package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.container.JsonContainer;
import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final TreeMap<Long, User> users = new TreeMap<>();
    private final UserMapper mapper;
    private final JsonContainer jsonContainer;
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public void delete(User user) {
        users.remove(user.getId());
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User update(User user) {
        users.replace(user.getId(), user);
        return users.get(user.getId());
    }

    @SneakyThrows
    @PostConstruct
    private void init() {
        for (UserCreateEditDto user : jsonContainer.users()) {
            User val = mapper.toUser(user);
            users.put(val.getId(), val);
        }
    }
}
