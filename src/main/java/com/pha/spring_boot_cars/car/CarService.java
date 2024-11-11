package com.pha.spring_boot_cars.car;

import com.pha.spring_boot_cars.car.dto.CarDto;
import com.pha.spring_boot_cars.car.exeption.CarAlreadyExistException;
import com.pha.spring_boot_cars.car.exeption.CarNotExistException;
import com.pha.spring_boot_cars.car.dto.CarUpdateRequestDTO;
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
    private CarMapper carMapper;

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto getById(Long id) {
        return carMapper.toDto(carRepository.getReferenceById(id));
    }

    public Optional<CarDto> findByRegistrationNumber(String registrationNumber) {
        return carRepository.findByRegistrationNumber(registrationNumber)
                .map(carMapper::toDto);
    }

    public CarDto addCar(Car car) {
        log.info("Adding a Car : {}", car.toString());
        if (carRepository.existsByRegistrationNumber(car.getRegistrationNumber())) {
            throw new CarAlreadyExistException();
        }
        return carMapper.toDto(carRepository.save(car));
    }

    public CarDto updateCar(String carRegNum, CarUpdateRequestDTO carUpdateRequest) {
        if (!carRepository.existsByRegistrationNumber(carRegNum)) {
            throw new CarNotExistException();
        }

        Car carFromDB = carRepository.findByRegistrationNumber(carRegNum)
                .map(car -> {
                    car.setPrice(carUpdateRequest.getPrice());
                    car.setKms(carUpdateRequest.getKms());
                    return car;
                }).orElseThrow();
        return carMapper.toDto(carRepository.save(carFromDB));
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
