package com.pha.spring_boot_cars.controller.exception;

public class CarAlreadyExistException extends RuntimeException {

    public CarAlreadyExistException() {
        super("Car already exist");
    }
}
