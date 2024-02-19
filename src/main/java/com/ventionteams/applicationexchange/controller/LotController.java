package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.service.ImageService;
import com.ventionteams.applicationexchange.dto.PageResponse;
import com.ventionteams.applicationexchange.service.LotService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/lots")
@RequiredArgsConstructor
@Validated
public class LotController {
    private final LotService lotService;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<PageResponse<LotReadDTO>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return  ok(PageResponse.of(lotService.findAll(page, limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotReadDTO> findById(@PathVariable("id") Long id) {
        return lotService.findById(id)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LotReadDTO> create(@RequestParam("lot") LotUpdateDTO lot, @RequestParam(value = "files") List<MultipartFile> files) {
        return ok(imageService.saveListImages(files, lotService.create(lot)));
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
