package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class AuctionEndedException extends BaseException {
    public AuctionEndedException(String message, HttpStatus status) {
        super(message, status);
    }
}
