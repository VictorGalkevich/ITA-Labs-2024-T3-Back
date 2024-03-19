package com.ventionteams.applicationexchange.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageUpdateDTO (
        MultipartFile file,
        boolean isMainImage
) {
}
