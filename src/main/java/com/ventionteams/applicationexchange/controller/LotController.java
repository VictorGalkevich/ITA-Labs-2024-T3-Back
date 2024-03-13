package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.*;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.enumeration.LotSortField;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.service.ImageService;
import com.ventionteams.applicationexchange.service.LotService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@ValidatedController
@RequestMapping("/lots")
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<PageResponse<LotReadDTO>> findLotsWithFilter(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                       @RequestParam(required = false) LotStatus lotStatus,
                                                                       @RequestParam(required = false) LotSortField sortField,
                                                                       @RequestParam(required = false) Sort.Direction sortOrder,
                                                                       @AuthenticationPrincipal Authentication principal) {
        final LotFilterDTO filter = new LotFilterDTO(null, null, null, null, null, null, null, null, null, null, null, lotStatus);
        final LotSortCriteria sort = LotSortCriteria.builder()
                .field(Optional.ofNullable(sortField).orElse(LotSortField.CREATED_AT))
                .order(Optional.ofNullable(sortOrder).orElse(Sort.Direction.DESC))
                .build();
        UUID id = null;
        if (principal != null) {
            UserAuthDto user = (UserAuthDto) principal.getPrincipal();
            id = user.id();
        }
        return ok(PageResponse.of(lotService.findAll(page, limit, filter, sort, id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotReadDTO> findById(@PathVariable("id") Long id,
                                               @AuthenticationPrincipal Authentication principal) {
        UUID uuid = null;
        if (principal != null) {
            UserAuthDto user = (UserAuthDto) principal.getPrincipal();
            uuid = user.id();
        }
        return lotService.findById(id, uuid)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LotReadDTO> create(@RequestBody LotUpdateDTO lot, @RequestBody List<MultipartFile> files) {
        return ok(imageService.saveListImages(files, lotService.create(lot)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotReadDTO> update(@PathVariable("id") Long id,
                                             @RequestBody LotUpdateDTO lotUpdateDTO,
                                             @AuthenticationPrincipal Authentication principal) {
        UserAuthDto user = (UserAuthDto) principal.getPrincipal();
        return lotService.update(id, lotUpdateDTO, user.id())
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
