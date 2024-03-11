package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.dto.PageResponse;
import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.service.BidService;
import com.ventionteams.applicationexchange.service.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@ValidatedController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BidService bidService;
    private static final String EMPTY = "";

    @GetMapping
    public ResponseEntity<PageResponse<UserReadDto>> findAll(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(userService.findAll(page, limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/{id}/bids")
    public ResponseEntity<PageResponse<BidReadDto>> findById(@PathVariable("id") Long id,
                                                             @RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(bidService.findBidsByUserId(id, page, limit)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReadDto> create(@RequestBody @Validated UserCreateEditDto dto) {
        return ok().body(userService.create(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'EMPLOYEE', 'USER')")
    public ResponseEntity<UserReadDto> update(@AuthenticationPrincipal Authentication principal,
                                              @RequestBody @Validated UserCreateEditDto dto) {
        UserReadDto user = (UserReadDto) principal.getPrincipal();
        return userService.update(user.id(), dto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'EMPLOYEE', 'USER')")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Authentication principal) {
        UserReadDto user = (UserReadDto) principal.getPrincipal();
        return userService.delete(user.id())
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'EMPLOYEE', 'USER')")
    public ResponseEntity<UserReadDto> findSelf(@AuthenticationPrincipal Authentication principal) {
        UserReadDto user = (UserReadDto) principal.getPrincipal();
        return ok().body(user);
    }
}
