package com.example.todolist.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException e) {
        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "404 Not Found");

        log.debug("{}", e);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException e) {
        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "400 Bad Request");
        body.put("errorMsg", e.getMessage());

        log.debug("{}", e);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    public ResponseEntity<Map<String, Object>> handleInvalidException(MethodArgumentNotValidException e) {

        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "400 Bad Request");
        body.put("errorMsg", getResultMessage(e.getBindingResult().getFieldErrors().iterator()));

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerError(Exception e) {
        Map<String, Object> body = new HashMap<>();

        body.put("Status Code", "500 Internal Server Error");

        log.error("{}", e);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected String getResultMessage(final Iterator<FieldError> it) {
        final StringBuilder msgBuilder = new StringBuilder();

        while (it.hasNext()) {
            final FieldError fieldError = it.next();
            msgBuilder
                    .append("['")
                    .append(fieldError.getField())
                    .append("' is '")
                    .append(fieldError.getRejectedValue())
                    .append("'. ")
                    .append(fieldError.getDefaultMessage())
                    .append("]");

            if (it.hasNext()) {
                msgBuilder.append(", ");
            }
        }

        return msgBuilder.toString();
    }

    protected String getPropertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }
}
