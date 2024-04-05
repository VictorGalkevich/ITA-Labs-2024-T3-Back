package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class EntityRelatedService {
    public static <T> void validateEntity(Optional<T> entity, Class<T> clazz) {
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("specified %s doesn't exist"
                    .formatted(clazz.getSimpleName()
                            .toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }
    }

    public static <T> void validateEntity(Optional<T> entity, Runnable onEmptyAction) {
        if (entity.isEmpty()) {
            onEmptyAction.run();
        }
    }
}
