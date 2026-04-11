package com.bibliotecams.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RolNotFoundException extends RuntimeException {
    public RolNotFoundException(String message) {
        super(message);
    }
}