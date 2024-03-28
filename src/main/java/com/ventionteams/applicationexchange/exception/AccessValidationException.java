package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class AccessValidationException extends BaseException {
    public AccessValidationException(String message, HttpStatus status) {
        super(message, status);
    }
}
