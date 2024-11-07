package com.pha.spring_boot_cars.controller.exception;

public class CarNotExistException extends RuntimeException {

    public CarNotExistException() {
        super("Car does not exist");
    }
}
