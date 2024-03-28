package com.ventionteams.applicationexchange.controller;


import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.create.OfferCreateEditDto;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.OfferReadDto;
import com.ventionteams.applicationexchange.dto.read.PageResponse;
import com.ventionteams.applicationexchange.entity.enumeration.OfferStatus;
import com.ventionteams.applicationexchange.service.OfferService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.ventionteams.applicationexchange.entity.enumeration.OfferStatus.CANCELLED;
import static com.ventionteams.applicationexchange.entity.enumeration.OfferStatus.READY_TO_BUY;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@ValidatedController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    public ResponseEntity<PageResponse<OfferReadDto>> findAll(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                              @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(offerService.findAll(page, limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferReadDto> findById(@PathVariable("id") Long id) {
        return offerService.findById(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<OfferReadDto> create(@RequestBody OfferCreateEditDto dto,
                                               @AuthenticationPrincipal UserAuthDto user) {
        return ok().body(offerService.create(dto, user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<OfferReadDto> delete(@PathVariable("id") Long id,
                                               @AuthenticationPrincipal UserAuthDto user) {
        return offerService.delete(id, user)
                ? noContent().build()
                : notFound().build();
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Void> reject(@PathVariable("id") Long id,
                                       @AuthenticationPrincipal UserAuthDto user) {
        return offerService.decide(id, user, CANCELLED).isPresent()
                ? ok().build()
                : notFound().build();
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<Void> approve(@PathVariable("id") Long id,
                                        @AuthenticationPrincipal UserAuthDto user) {
        return offerService.decide(id, user, READY_TO_BUY).isPresent()
                ? ok().build()
                : notFound().build();
    }

    @PostMapping("/{id}/sell")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<OfferReadDto> sell(@PathVariable("id") Long id,
                                             @AuthenticationPrincipal UserAuthDto user) {
        return offerService.sell(id, user)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }
}
