package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class EntityStatusViolationException extends BaseException {
    public EntityStatusViolationException(String message, HttpStatus status) {
        super(message, status);
    }
}
