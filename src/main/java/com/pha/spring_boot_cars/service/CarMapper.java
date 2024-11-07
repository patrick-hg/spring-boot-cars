package com.pha.spring_boot_cars.service;

import com.pha.spring_boot_cars.repository.Car;

public class CarMapper {

    public static CarDTO toDTO(Car car) {
        CarDTO carDto = new CarDTO();
        carDto.setBrandName(car.getBrandName());
        carDto.setModelName(car.getModelName());
        carDto.setCarType(car.getCarType());
        carDto.setPrice(car.getPrice());
        carDto.setKms(car.getKms());
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setYear(car.getYear());
        return carDto;
    }

    public static Car toEntity(CarDTO carDto) {
        Car car = new Car();
        car.setBrandName(carDto.getBrandName());
        car.setModelName(carDto.getModelName());
        car.setCarType(carDto.getCarType());
        car.setPrice(carDto.getPrice());
        car.setKms(carDto.getKms());
        car.setRegistrationNumber(carDto.getRegistrationNumber());
        car.setYear(carDto.getYear());
        return car;
    }

}
