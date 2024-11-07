package com.pha.spring_boot_cars.controller;

import com.pha.spring_boot_cars.service.CarDTO;
import com.pha.spring_boot_cars.service.CarMapper;
import com.pha.spring_boot_cars.service.CarService;
import com.pha.spring_boot_cars.service.CarUpdateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CarController {

    private CarService carService;

    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.addCar(CarMapper.toEntity(carDTO)));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllCars() {
        carService.deleteAllCars();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping(path = "/{carRegNum}")
    public Optional<CarDTO> findByRegistrationNumber(@PathVariable String carRegNum) {
        return carService.findByRegistrationNumber(carRegNum);
    }

    @PutMapping(path = "/{carRegNum}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable String carRegNum,
                                            @RequestBody CarUpdateRequestDTO carUpdateRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.updateCar(carRegNum, carUpdateRequestDTO));
    }

    @DeleteMapping(path = "/{carRegNum}")
    public ResponseEntity<?> deleteCar(@PathVariable String carRegNum) {
        carService.deleteCar(carRegNum);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsForCarsCollection() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)
                .build();
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsForSpecificCar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }

}
