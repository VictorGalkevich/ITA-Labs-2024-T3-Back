package com.ventionteams.applicationexchange.handler;

import com.ventionteams.applicationexchange.exception.AuctionEndedException;
import com.ventionteams.applicationexchange.exception.IllegalPriceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.ventionteams.applicationexchange.controller")
public class LotExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalPriceException.class)
    public ResponseEntity<String> handle(IllegalPriceException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(AuctionEndedException.class)
    public ResponseEntity<String> handle(AuctionEndedException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
