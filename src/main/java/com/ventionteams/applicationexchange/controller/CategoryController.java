package com.ventionteams.applicationexchange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventionteams.applicationexchange.container.CategoryContainer;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<String> findAll() {
        List<CategoryReadDto> all = categoryService.findAll();
        return all.isEmpty()
                ? noContent().build()
                : ok().body(getFromObject(new CategoryContainer(all)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id)
                .map(obj -> ok()
                        .body(getFromObject(obj)))
                .orElseGet(notFound()::build);
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
    public ResponseEntity<String> update(@PathVariable("id") Integer id,
                                         @RequestBody String json) {
        CategoryCreateEditDto dto = objectMapper.readValue(json, CategoryCreateEditDto.class);
        return categoryService.update(id, dto)
                .map(obj -> ok().body(getFromObject(obj)))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

    @SneakyThrows
    private String getFromObject(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
