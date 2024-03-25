package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class UserNotRegisteredException extends BaseException {
    public UserNotRegisteredException(String message, HttpStatus status) {
        super(message, status);
    }
}
