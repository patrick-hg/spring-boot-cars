package com.pha.spring_boot_cars.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        DetailledErrorResponse detailledErrorResponse = new DetailledErrorResponse(
                new Date(), ex.getMessage(),"Unexpected internal error");
        return new ResponseEntity<>(detailledErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
