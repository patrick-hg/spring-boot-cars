package com.pha.spring_boot_cars.car.exeption;

public class CarNotExistException extends RuntimeException {

    public CarNotExistException() {
        super("Car does not exist");
    }
}
