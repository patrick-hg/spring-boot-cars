package com.pha.spring_boot_cars.car.exeption;

import com.pha.spring_boot_cars.commons.DetailledErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CarExceptionHandler {

    @ExceptionHandler(CarAlreadyExistException.class)
    public ResponseEntity<?> handleCarAlreadyExistException(CarAlreadyExistException ex) {
        DetailledErrorResponse detailledErrorResponse = new DetailledErrorResponse(
                new Date(), ex.getMessage(), "This car is already registered");
        return new ResponseEntity<>(detailledErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarNotExistException.class)
    public ResponseEntity<?> handleCarNotExistException(CarNotExistException ex) {
        DetailledErrorResponse detailledErrorResponse = new DetailledErrorResponse(
                new Date(), ex.getMessage(), "The car is not found");
        return new ResponseEntity<>(detailledErrorResponse, HttpStatus.NOT_FOUND);
    }
}
