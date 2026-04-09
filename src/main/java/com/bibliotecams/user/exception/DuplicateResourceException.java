package com.bibliotecams.user.exception;

// Cuando intentas registrar un DNI o email que YA EXISTE

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s ya existe con %s : '%s'", resourceName, fieldName, fieldValue));
    }
}