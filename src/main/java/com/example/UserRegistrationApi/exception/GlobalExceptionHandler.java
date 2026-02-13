package com.example.UserRegistrationApi.exception;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler (UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFound(UserNotFoundException ex)
    {
        Map<String,Object> error=new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message",ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
