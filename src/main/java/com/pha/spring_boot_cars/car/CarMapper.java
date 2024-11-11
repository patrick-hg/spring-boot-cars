package com.pha.spring_boot_cars.car;

import com.pha.spring_boot_cars.car.dto.CarDto;
import com.pha.spring_boot_cars.commons.CommonMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements CommonMapper<Car, CarDto> {

    public Car toEntity(CarDto carDto) {
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

    public CarDto toDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setBrandName(car.getBrandName());
        carDto.setModelName(car.getModelName());
        carDto.setCarType(car.getCarType());
        carDto.setPrice(car.getPrice());
        carDto.setKms(car.getKms());
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setYear(car.getYear());
        return carDto;
    }
}
