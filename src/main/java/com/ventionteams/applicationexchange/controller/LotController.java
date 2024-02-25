package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.dto.PageResponse;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.enumeration.LotSortField;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import com.ventionteams.applicationexchange.service.LotService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/lots")
@RequiredArgsConstructor
@Validated
public class LotController {
    private final LotService lotService;

    @GetMapping
    public ResponseEntity<PageResponse<LotReadDTO>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                            @RequestParam(required = false) List<Integer> categories,
                                                            @RequestParam(required = false) List<Packaging> packaging,
                                                            @RequestParam(required = false) List<Integer> locations,
                                                            @RequestParam(required = false) List<String> varieties,
                                                            @RequestParam(required = false) List<Weight> weights,
                                                            @RequestParam(required = false) Long fromQuantity,
                                                            @RequestParam(required = false) Long toQuantity,
                                                            @RequestParam(required = false) Integer fromSize,
                                                            @RequestParam(required = false) Integer toSize,
                                                            @RequestParam(required = false) LotSortField sortField,
                                                            @RequestParam(required = false) Sort.Direction sortOrder) {
        final LotFilterDTO filter = new LotFilterDTO(categories, packaging, locations, varieties, weights, fromQuantity, toQuantity, fromSize, toSize);
        final LotSortCriteria sort = LotSortCriteria.builder()
                .field(Optional.ofNullable(sortField).orElse(LotSortField.CREATED_AT))
                .order(Optional.ofNullable(sortOrder).orElse(Sort.Direction.DESC))
                .build();
        return ok(PageResponse.of(lotService.findAll(page, limit, filter, sort)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotReadDTO> findById(@PathVariable("id") Long id) {
        return lotService.findById(id)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LotReadDTO> create(@RequestBody LotUpdateDTO lot) {
        return ok(lotService.create(lot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotReadDTO> update(@PathVariable("id") Long id,
                                             @RequestBody LotUpdateDTO lotUpdateDTO) {
        return lotService.update(id, lotUpdateDTO)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return lotService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
