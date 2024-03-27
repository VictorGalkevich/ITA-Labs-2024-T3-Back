package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.dto.PageResponse;
import com.ventionteams.applicationexchange.dto.UserAuthDto;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@ValidatedController
@RequestMapping("/lots")
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<PageResponse<LotReadDTO>> findLotsByStatus(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                       @RequestParam LotStatus lotStatus,
                                                                       @RequestParam(required = false) LotSortField sortField,
                                                                       @RequestParam(required = false) Sort.Direction sortOrder,
                                                                       @AuthenticationPrincipal UserAuthDto user) {
        final LotSortCriteria sort = LotSortCriteria.builder()
                .field(Optional.ofNullable(sortField).orElse(LotSortField.CREATED_AT))
                .order(Optional.ofNullable(sortOrder).orElse(Sort.Direction.DESC))
                .build();
        UUID id = null;
        if (user != null) {
            id = user.id();
        }
        return ok(PageResponse.of(lotService.findByStatus(page, limit, lotStatus, sort, id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotReadDTO> findById(@PathVariable("id") Long id,
                                               @AuthenticationPrincipal UserAuthDto user) {
        UUID uuid = null;
        if (user != null) {
            uuid = user.id();
        }
        return lotService.findById(id, uuid)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LotReadDTO> create(@RequestPart @Validated LotUpdateDTO lot,
                                             @RequestPart List<MultipartFile> images,
                                             @AuthenticationPrincipal UserAuthDto user) {
        return ok().body(imageService.saveListImagesForLot(images, lotService.create(lot, user)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<LotReadDTO> update(@PathVariable("id") Long id,
                                                                                         @RequestBody LotUpdateDTO lotUpdateDTO,
                                                                                         @RequestPart List<MultipartFile> newImages,
                                                                                         @AuthenticationPrincipal UserAuthDto user) {
        return lotService.update(id, lotUpdateDTO, user, newImages)
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
