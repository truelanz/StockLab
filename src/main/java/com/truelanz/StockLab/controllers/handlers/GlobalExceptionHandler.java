package com.truelanz.StockLab.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Erros de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError err = new ValidationError(
                Instant.now(),
                status.value(),
                "Erro de validação",
                "Campos inválidos",
                request.getRequestURI()
        );

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    // Erro genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> generic(
            Exception e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Erro interno",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
