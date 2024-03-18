package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class IllegalPriceException extends BaseException{
    public IllegalPriceException(String message, HttpStatus status) {
        super(message, status);
    }
}
