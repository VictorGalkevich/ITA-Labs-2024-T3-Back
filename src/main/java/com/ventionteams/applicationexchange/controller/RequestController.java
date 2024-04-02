package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.create.RequestCreateEditDto;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.PageResponse;
import com.ventionteams.applicationexchange.dto.read.RequestReadDto;
import com.ventionteams.applicationexchange.service.RequestService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@ValidatedController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    public ResponseEntity<PageResponse<RequestReadDto>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                                @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                @RequestParam(defaultValue = "ACTIVE") String status) {
        return ok(PageResponse.of(requestService.findAll(page, limit, status)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestReadDto> findById(@PathVariable("id") Long id) {
        return requestService.findById(id)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping
    public ResponseEntity<RequestReadDto> create(@RequestBody @Validated RequestCreateEditDto dto,
                                             @AuthenticationPrincipal UserAuthDto user) {
        return ok().body(requestService.create(dto, user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<RequestReadDto> update(@PathVariable("id") Long id,
                                             @RequestBody RequestCreateEditDto dto,
                                             @AuthenticationPrincipal UserAuthDto user) {
        return requestService.update(id, dto, user)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id,
                                       @AuthenticationPrincipal UserAuthDto user) {
        return requestService.delete(id, user)
                ? noContent().build()
                : notFound().build();
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public ResponseEntity<Void> reject(@PathVariable Long id,
                                       @RequestBody String message) {
        return requestService.reject(id, message).isPresent()
                ? ok().build()
                : notFound().build();
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        return requestService.approve(id).isPresent()
                ? ok().build()
                : notFound().build();
    }
}
