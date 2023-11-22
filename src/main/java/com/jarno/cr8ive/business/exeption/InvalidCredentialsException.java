package com.jarno.cr8ive.business.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCredentialsException extends ResponseStatusException {
    public InvalidCredentialsException(){
        super(HttpStatus.BAD_REQUEST, "INVALID_CREDENTIALS");
    }
}
