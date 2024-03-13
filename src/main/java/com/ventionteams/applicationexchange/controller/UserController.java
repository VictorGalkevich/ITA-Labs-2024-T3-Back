package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.*;
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

import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@ValidatedController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BidService bidService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<PageResponse<UserReadDto>> findAll(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(userService.findAll(page, limit)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserReadDto> findById(@PathVariable("id") String id) {
        return userService.findById(UUID.fromString(id))
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/bids")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PageResponse<BidReadDto>> findById(@AuthenticationPrincipal Authentication principal,
                                                             @RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        UserAuthDto user = (UserAuthDto) principal.getPrincipal();
        return ok().body(PageResponse.of(bidService.findBidsByUserId(user.id(), page, limit)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserReadDto> create(@RequestBody @Validated UserData data,
                                              @AuthenticationPrincipal Authentication principal) {
        UserAuthDto user = (UserAuthDto) principal.getPrincipal();
        UserCreateEditDto dto = toDto(user, data);
        return ok().body(userService.create(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'USER')")
    public ResponseEntity<UserReadDto> update(@AuthenticationPrincipal Authentication principal,
                                              @RequestBody @Validated UserData data) {
        UserAuthDto user = (UserAuthDto) principal.getPrincipal();
        UserCreateEditDto dto = toDto(user, data);
        return userService.update(user.id(), dto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'USER')")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Authentication principal) {
        UserAuthDto user = (UserAuthDto) principal.getPrincipal();
        return userService.delete(user.id())
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'USER')")
    public ResponseEntity<UserReadDto> findSelf(@AuthenticationPrincipal Authentication principal) {
        UserAuthDto user = (UserAuthDto) principal.getPrincipal();
        return userService.findById(user.id())
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    private UserCreateEditDto toDto(UserAuthDto user, UserData data) {
        return new UserCreateEditDto(
                user.id(),
                data.firstName(),
                data.lastName(),
                data.email(),
                user.authorities().getFirst(),
                data.phoneNumber(),
                data.currency()
        );
    }
}
