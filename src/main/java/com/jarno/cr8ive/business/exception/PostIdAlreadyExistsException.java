package com.jarno.cr8ive.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostIdAlreadyExistsException extends ResponseStatusException {
    public PostIdAlreadyExistsException(){super (HttpStatus.BAD_REQUEST, "POST_Id already exist");}
}
