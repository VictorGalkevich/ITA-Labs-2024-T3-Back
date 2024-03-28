package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class PermissionsDeniedException extends BaseException {
    public PermissionsDeniedException(String message, HttpStatus status) {
        super(message, status);
    }
}
