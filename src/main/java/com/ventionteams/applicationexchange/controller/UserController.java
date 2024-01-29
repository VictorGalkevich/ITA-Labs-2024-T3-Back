package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<UserReadDto>> findAll() {
        List<UserReadDto> all = userService.findAll();
        return all.isEmpty()
                ? notFound().build()
                : ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReadDto> create(@RequestBody UserCreateEditDto dto) {
        return ok().body(userService.create(dto));
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<UserReadDto> update(@PathVariable("id") Long id,
                                                  @RequestBody UserCreateEditDto dto) {
        return userService.update(id, dto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
