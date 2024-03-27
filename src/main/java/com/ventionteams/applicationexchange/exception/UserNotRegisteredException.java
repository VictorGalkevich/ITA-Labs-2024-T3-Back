package com.ventionteams.applicationexchange.exception;

import org.springframework.http.HttpStatus;

public class UserNotRegisteredException extends BaseException {
    public UserNotRegisteredException() {
        super("You have not completed the onboarding", HttpStatus.FORBIDDEN);
    }
}
