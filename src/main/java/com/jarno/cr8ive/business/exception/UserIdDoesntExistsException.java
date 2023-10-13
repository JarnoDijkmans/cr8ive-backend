package com.jarno.cr8ive.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIdDoesntExistsException extends ResponseStatusException {
    public UserIdDoesntExistsException(){super (HttpStatus.BAD_REQUEST, "User ID doesn't exist");}
}
