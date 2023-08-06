package com.rscinema.finalproject.controller.advice;

import com.rscinema.finalproject.domain.exception.GenericExceptionResponse;
import com.rscinema.finalproject.domain.exception.NoContentException;
import com.rscinema.finalproject.domain.exception.PasswordException;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericExceptionResponse> handleResourceNotFound (
            ResourceNotFoundException exception, HttpServletRequest request
            ){
        GenericExceptionResponse response = GenericExceptionResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<GenericExceptionResponse> handleNoContent (
            NoContentException exception, HttpServletRequest request
    ){
        GenericExceptionResponse response = GenericExceptionResponse.builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .path(request.getRequestURI())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<GenericExceptionResponse> handleNoContent (
            PasswordException exception, HttpServletRequest request
    ){
        GenericExceptionResponse response = GenericExceptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
