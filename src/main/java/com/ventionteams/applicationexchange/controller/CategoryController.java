package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.*;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.enumeration.LotSortField;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import com.ventionteams.applicationexchange.service.CategoryService;
import com.ventionteams.applicationexchange.service.LotService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<PageResponse<LotReadDTO>> findLotsWithFilter(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                            @PathVariable("id") @Min(1) @Max(1000) Integer category,
                                                            @RequestParam(required = false) List<Packaging> packaging,
                                                            @RequestParam(required = false) List<Integer> locations,
                                                            @RequestParam(required = false) List<Integer> varieties,
                                                            @RequestParam(required = false) List<Weight> weights,
                                                            @RequestParam(required = false) @Min(1) @Max(1000) Long fromQuantity,
                                                            @RequestParam(required = false) @Min(1) @Max(1000) Long toQuantity,
                                                            @RequestParam(required = false) @Min(1) @Max(1000) Integer fromSize,
                                                            @RequestParam(required = false) @Min(1) @Max(1000) Integer toSize,
                                                            @RequestParam(required = false) @Min(1) @Max(100000) Integer fromPrice,
                                                            @RequestParam(required = false) @Min(1) @Max(100000) Integer toPrice,
                                                            @RequestParam(required = false) LotSortField sortField,
                                                            @RequestParam(required = false) Sort.Direction sortOrder) {
        final LotFilterDTO filter = new LotFilterDTO(category, packaging, locations, varieties, weights, fromQuantity, toQuantity, fromSize, toSize, fromPrice, toPrice, LotStatus.ACTIVE);
        final LotSortCriteria sort = LotSortCriteria.builder()
                .field(Optional.ofNullable(sortField).orElse(LotSortField.CREATED_AT))
                .order(Optional.ofNullable(sortOrder).orElse(Sort.Direction.DESC))
                .build();
        return ok(PageResponse.of(lotService.findAll(page, limit, filter, sort, 123123L)));
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
