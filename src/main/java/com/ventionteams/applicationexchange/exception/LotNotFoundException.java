package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class LotNotFoundException extends BaseException {

    public LotNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
