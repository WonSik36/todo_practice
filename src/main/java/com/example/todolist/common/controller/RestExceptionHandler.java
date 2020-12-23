package com.example.todolist.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(NoSuchElementException e) {
        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "404 Not Found");

        log.debug("{}", e);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> badRequestHandler(IllegalArgumentException e) {
        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "400 Bad Request");
        body.put("errorMsg", e.getMessage());

        log.debug("{}", e);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> internalServerErrorHandler(Exception e) {
        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "500 Internal Server Error");

        log.error("{}", e);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
