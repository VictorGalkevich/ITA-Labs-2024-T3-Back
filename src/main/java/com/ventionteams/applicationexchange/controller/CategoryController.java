package com.ventionteams.applicationexchange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<CategoryReadDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryReadDto findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @SneakyThrows
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryReadDto create(@RequestBody String json) {
        CategoryCreateEditDto dto = objectMapper.readValue(json, CategoryCreateEditDto.class);
        return categoryService.create(dto);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public CategoryReadDto update(@PathVariable("id") Integer id,
                                  @RequestBody String json) {
        CategoryCreateEditDto dto = objectMapper.readValue(json, CategoryCreateEditDto.class);
        return categoryService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
