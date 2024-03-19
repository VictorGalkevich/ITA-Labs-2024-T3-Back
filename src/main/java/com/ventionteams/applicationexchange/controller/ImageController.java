package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<Image> getAvatar(@PathVariable("id") Long id) {
        return imageService.getAvatar(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }
}
