package com.ventionteams.applicationexchange.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T> (
        List<T> content,
        Metadata metadata
) {
    public static <T> PageResponse<T> of(Page<T> page) {
        Metadata metadata = new Metadata(page.getNumber() + 1, page.getSize(), page.getTotalElements());
        return new PageResponse<>(page.getContent(), metadata);
    }

    public record Metadata (
            int page,
            int size,
            long totalElements
    ) {

    }

}
