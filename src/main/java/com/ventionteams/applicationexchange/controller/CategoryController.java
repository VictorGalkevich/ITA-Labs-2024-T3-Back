package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.*;
import com.ventionteams.applicationexchange.service.CategoryService;
import com.ventionteams.applicationexchange.service.LotService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@ValidatedController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final LotService lotService;

    @GetMapping
    public ResponseEntity<List<MainPageCategoryReadDto>> findAll() {
        return ok().body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryReadDto> findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/{id}/lots")
    public ResponseEntity<PageResponse<LotReadDTO>> findByCategoryId(@PathVariable("id") Integer id,
                                                                     @RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                                     @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return  ok().body(PageResponse.of(lotService.findLotsByCategoryId(id, page, limit)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryReadDto> create(@RequestBody CategoryCreateEditDto dto) {
        return ok().body(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryReadDto> update(@PathVariable("id") Integer id,
                                                  @RequestBody CategoryCreateEditDto dto) {
        return categoryService.update(id, dto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
