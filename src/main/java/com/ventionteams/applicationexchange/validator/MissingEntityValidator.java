package com.ventionteams.applicationexchange.validator;

import com.ventionteams.applicationexchange.annotation.Validator;
import com.ventionteams.applicationexchange.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Validator
public class MissingEntityValidator {
    public <T> void validate(Optional<T> entity, Class<T> clazz) {
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("specified %s doesn't exist"
                    .formatted(clazz.getSimpleName()
                            .toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }
    }

    public <T> void validate(Optional<T> entity, Runnable task) {
        if (entity.isEmpty()) {
            task.run();
        }
    }
}
