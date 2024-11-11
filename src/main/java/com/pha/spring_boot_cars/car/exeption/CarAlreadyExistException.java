package com.pha.spring_boot_cars.car.exeption;

public class CarAlreadyExistException extends RuntimeException {

    public CarAlreadyExistException() {
        super("Car already exist");
    }
}
