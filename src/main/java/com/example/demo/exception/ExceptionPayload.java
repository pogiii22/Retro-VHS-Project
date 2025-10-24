package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionPayload {

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

    public ExceptionPayload(String message, HttpStatus httpStatus, LocalDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
