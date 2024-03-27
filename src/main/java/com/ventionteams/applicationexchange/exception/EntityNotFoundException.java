package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
