package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler (value = {DuplicateResourceException.class})
    public ResponseEntity<Object> handleDuplicateResourceException (DuplicateResourceException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionPayload exceptionPayload = new ExceptionPayload(e.getMessage(), badRequest, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, badRequest);
    }

    @ExceptionHandler (value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleDuplicateResourceException (ResourceNotFoundException e){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        ExceptionPayload exceptionPayload = new ExceptionPayload(e.getMessage(), badRequest, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, badRequest);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }
}

