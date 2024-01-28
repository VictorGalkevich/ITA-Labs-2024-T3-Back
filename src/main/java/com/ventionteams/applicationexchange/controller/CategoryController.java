package com.ventionteams.applicationexchange.controller;

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

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<CategoryReadDto>> findAll() {
        List<CategoryReadDto> all = categoryService.findAll();
        return all.isEmpty()
                ? notFound().build()
                : ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryReadDto> findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryReadDto> create(@RequestBody CategoryCreateEditDto dto) {
        return ok().body(categoryService.create(dto));
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<CategoryReadDto> update(@PathVariable("id") Integer id,
                                         @RequestBody CategoryCreateEditDto dto) {
        return categoryService.update(id, dto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
