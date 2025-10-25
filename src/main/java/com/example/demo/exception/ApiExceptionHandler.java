package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler (value = {DuplicateResourceException.class})
    public ResponseEntity<Object> handleDuplicateResourceException (DuplicateResourceException ex, HttpServletRequest req){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        log.warn("Exception msg: {} at path={}, method = {}", ex.getMessage(), req.getRequestURI(), req.getMethod());
        ExceptionPayload exceptionPayload = new ExceptionPayload(ex.getMessage(), badRequest, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, badRequest);
    }

    @ExceptionHandler (value = {ResourceNotAvailableException.class})
    public ResponseEntity<Object> handleDuplicateResourceException (ResourceNotAvailableException ex, HttpServletRequest req){
        HttpStatus badRequest = HttpStatus.LOCKED;
        log.warn("Exception msg: {} at path={}, method = {}", ex.getMessage(), req.getRequestURI(), req.getMethod());
        ExceptionPayload exceptionPayload = new ExceptionPayload(ex.getMessage(), badRequest, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, badRequest);
    }

    @ExceptionHandler (value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleDuplicateResourceException (ResourceNotFoundException ex, HttpServletRequest req){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        log.warn("Exception msg: {} at path={}, method = {}", ex.getMessage(), req.getRequestURI(), req.getMethod());
        ExceptionPayload exceptionPayload = new ExceptionPayload(ex.getMessage(), badRequest, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, badRequest);
    }

    @ExceptionHandler (value = {NoResourceFoundException.class})
    public ResponseEntity<Object> handleDuplicateResourceException (NoResourceFoundException ex, HttpServletRequest req){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        log.warn("Exception msg: {} at path={}, method = {}", ex.getMessage(), req.getRequestURI(), req.getMethod());
        ExceptionPayload exceptionPayload = new ExceptionPayload("Not here!", badRequest, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, badRequest);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        log.warn("Exception msg: {} at path={}, method = {}", body, req.getRequestURI(), req.getMethod());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionPayload> handleAll(Exception ex, HttpServletRequest req) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("Unhandled exception: message={} ex={} path={} method={}",
                ex.getMessage(),
                ex,
                req.getRequestURI(),
                req.getMethod());
        ExceptionPayload exceptionPayload = new ExceptionPayload(ex.getMessage(), status, LocalDateTime.now());
        return new ResponseEntity<>(exceptionPayload, status);

    }
}

