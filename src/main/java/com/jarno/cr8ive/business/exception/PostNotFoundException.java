package com.jarno.cr8ive.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostNotFoundException extends ResponseStatusException {
    public PostNotFoundException(long postId) {
        super(HttpStatus.NOT_FOUND, "Post with ID " + postId + " not found");
    }
}
