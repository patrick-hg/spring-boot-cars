package com.pha.spring_boot_cars.service;

import com.pha.spring_boot_cars.controller.exception.CarAlreadyExistException;
import com.pha.spring_boot_cars.controller.exception.CarNotExistException;
import com.pha.spring_boot_cars.repository.Car;
import com.pha.spring_boot_cars.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CarService {

    private CarRepository carRepository;

    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarDTO getById(Long id) {
        return CarMapper.toDTO(carRepository.getReferenceById(id));
    }

    public Optional<CarDTO> findByRegistrationNumber(String registrationNumber) {
        return carRepository.findByRegistrationNumber(registrationNumber)
                .map(CarMapper::toDTO);
    }

    public CarDTO addCar(Car car) {
        log.info("Adding a Car : {}", car.toString());
        if (carRepository.existsByRegistrationNumber(car.getRegistrationNumber())) {
            throw new CarAlreadyExistException();
        }
        return CarMapper.toDTO(carRepository.save(car));
    }

    public CarDTO updateCar(String carRegNum, CarUpdateRequestDTO carUpdateRequest) {
        if (!carRepository.existsByRegistrationNumber(carRegNum)) {
            throw new CarNotExistException();
        }

        Car carFromDB = carRepository.findByRegistrationNumber(carRegNum)
                .map(car -> {
                    car.setPrice(carUpdateRequest.getPrice());
                    car.setKms(carUpdateRequest.getKms());
                    return car;
                }).orElseThrow();
        return CarMapper.toDTO(carRepository.save(carFromDB));
    }

    public void deleteCar(String carRegNum) {
        Optional<Car> car = carRepository.findByRegistrationNumber(carRegNum);
        if (car.isEmpty()) {
            throw new CarNotExistException();
        }
        car.ifPresent(carRepository::delete);
    }

    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
