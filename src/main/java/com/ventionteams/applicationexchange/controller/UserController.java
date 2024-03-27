package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.annotation.ValidatedController;
import com.ventionteams.applicationexchange.dto.BidForUserDto;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.PageResponse;
import com.ventionteams.applicationexchange.dto.UserAuthDto;
import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserData;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.LotSortField;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.service.BidService;
import com.ventionteams.applicationexchange.service.LotService;
import com.ventionteams.applicationexchange.service.UserService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@ValidatedController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BidService bidService;
    private final LotService lotService;

    @GetMapping
    public ResponseEntity<PageResponse<UserReadDto>> findAll(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(userService.findAll(page, limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable("id") String id) {
        return userService.findById(UUID.fromString(id))
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/bids")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<PageResponse<BidForUserDto>> findById(@AuthenticationPrincipal UserAuthDto user,
                                                                @RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                                @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                @RequestParam(defaultValue = "LEADING") BidStatus status) {
        return ok().body(PageResponse.of(bidService.findBidsByUserId(user.id(), page, limit, status)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReadDto> create(@RequestPart @Validated UserData data,
                                              @AuthenticationPrincipal UserAuthDto user, @RequestPart(required = false) MultipartFile avatar) {
        UserCreateEditDto dto = toDto(user, data);  
        return ok().body(userService.create(dto, avatar));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserReadDto> update(@AuthenticationPrincipal UserAuthDto user,
                                              @RequestPart @Validated UserData data, @RequestPart(required = false) MultipartFile newAvatar) {
        UserCreateEditDto dto = toDto(user, data);
        return userService.update(user.id(), dto, newAvatar)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserAuthDto user) {
        return userService.delete(user.id())
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'USER')")
    public ResponseEntity<UserReadDto> findSelf(@AuthenticationPrincipal UserAuthDto user) {
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
                user.email(),
                user.authorities().getFirst(),
                data.phoneNumber(),
                data.currency()
        );
    }

    @GetMapping("/lots")
    public ResponseEntity<PageResponse<LotReadDTO>> findLotsWithFilter(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                       @AuthenticationPrincipal UserAuthDto user,
                                                                       @RequestParam LotStatus status,
                                                                       @RequestParam(required = false) LotSortField sortField,
                                                                       @RequestParam(required = false) Sort.Direction sortOrder) {
        final LotSortCriteria sort = LotSortCriteria.builder()
                .field(Optional.ofNullable(sortField).orElse(LotSortField.CREATED_AT))
                .order(Optional.ofNullable(sortOrder).orElse(Sort.Direction.DESC))
                .build();
        UUID id = null;
        if (user != null) {
            id = user.id();
        }
        return ok(PageResponse.of(lotService.findUsersLotsByStatus(page, limit, status, sort, id)));
    }
}
