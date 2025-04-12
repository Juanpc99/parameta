package com.parameta.prueba.infraestructure.in.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String defaultMessage = error.getDefaultMessage();

            error.getCode();
            if (error.getCode().startsWith("typeMismatch")) {
                defaultMessage = "Formato invalido para el campo '" + field + "'. Usa el formato yyyy-MM-dd.";
            }

            errors.put(field, defaultMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}

