package com.bibliotecams.user.exception;

import com.bibliotecams.user.constants.AppConstants;
import com.bibliotecams.user.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones.
 * Convierte excepciones en respuestas HTTP estructuradas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleDuplicateResourceException(
            DuplicateResourceException ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBusinessException(
            BusinessException ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
        response.put("dateError", LocalDate.now());
        response.put("message", "Error de validación en los campos");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleBadCredentialsException(
            BadCredentialsException ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                LocalDate.now(),
                AppConstants.MSG_CREDENCIALES_INCORRECTAS,
                request.getDescription(false)
        );
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleAccessDeniedException(
            AccessDeniedException ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                LocalDate.now(),
                "Acceso denegado: " + ex.getMessage(),
                request.getDescription(false)
        );
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleIllegalArgumentException(
            IllegalArgumentException ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                "Valor inválido: " + ex.getMessage(),
                request.getDescription(false)
        );
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGlobalException(
            Exception ex,
            WebRequest request) {
        
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDate.now(),
                "Error interno del servidor: " + ex.getMessage(),
                request.getDescription(false)
        );
    }
}