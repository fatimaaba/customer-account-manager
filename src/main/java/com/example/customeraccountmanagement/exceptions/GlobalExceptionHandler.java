package com.example.customeraccountmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerIsAlreadyExistException.class)
    public ResponseEntity<Object> handleUserNotFoundException(CustomerIsAlreadyExistException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Customer is already exist");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotExistException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(CustomerNotExistException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Customer not exist");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerTransactionNotExistException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(CustomerTransactionNotExistException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "CustomerTransaction not exist");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
//
//    @ExceptionHandler(DuplicateEmailException.class)
//    public ResponseEntity<Object> handleDuplicateEmailException(DuplicateEmailException ex, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "Email is duplicate");
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
}
