package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.MainPageCategoryReadDto;
import com.ventionteams.applicationexchange.dto.PageResponse;
import com.ventionteams.applicationexchange.dto.UserAuthDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

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
                                                                       @AuthenticationPrincipal UserAuthDto user,
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
        UUID id = null;
        if (user != null) {
            id = user.id();
        }
        return ok(PageResponse.of(lotService.findAll(page, limit, filter, sort, id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryReadDto> create(@RequestBody CategoryCreateEditDto dto) {
        return ok().body(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryReadDto> update(@PathVariable("id") Integer id,
                                                  @RequestBody CategoryCreateEditDto dto) {
        return categoryService.update(id, dto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
