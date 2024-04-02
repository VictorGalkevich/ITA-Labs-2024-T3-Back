package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.create.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.read.CategoryReadDto;
import com.ventionteams.applicationexchange.dto.create.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.read.LotReadDTO;
import com.ventionteams.applicationexchange.dto.read.MainPageCategoryReadDto;
import com.ventionteams.applicationexchange.dto.read.PageResponse;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
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
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
                                                                       @RequestParam(required = false) Sort.Direction sortOrder,
                                                                       @RequestParam(required = false) String keyword) {
        final LotFilterDTO filter = new LotFilterDTO(category, packaging, locations, varieties, weights, fromQuantity, toQuantity, fromSize, toSize, fromPrice, toPrice, LotStatus.ACTIVE, keyword);
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryReadDto> create(@RequestPart CategoryCreateEditDto dto, @RequestPart(required = false) MultipartFile image) {
        return ok().body(categoryService.create(dto, image));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryReadDto> update(@PathVariable("id") Integer id,
                                                  @RequestPart CategoryCreateEditDto dto, @RequestPart(required = false) MultipartFile newImage) {
        return categoryService.update(id, dto, newImage)
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
