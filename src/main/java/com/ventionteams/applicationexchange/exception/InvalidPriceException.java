package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class InvalidPriceException extends BaseException {
    public InvalidPriceException(String message, HttpStatus status) {
        super(message, status);
    }
}
