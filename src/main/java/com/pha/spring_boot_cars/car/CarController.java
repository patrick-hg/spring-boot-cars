package com.pha.spring_boot_cars.car;

import com.pha.spring_boot_cars.car.dto.CarDto;
import com.pha.spring_boot_cars.car.dto.CarUpdateRequestDTO;
import lombok.AllArgsConstructor;
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
    private CarMapper carMapper;

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.addCar(carMapper.toEntity(carDTO)));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllCars() {
        carService.deleteAllCars();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping(path = "/{carRegNum}")
    public Optional<CarDto> findByRegistrationNumber(@PathVariable String carRegNum) {
        return carService.findByRegistrationNumber(carRegNum);
    }

    @PutMapping(path = "/{carRegNum}")
    public ResponseEntity<CarDto> updateCar(@PathVariable String carRegNum,
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

//    @RequestMapping(method = RequestMethod.OPTIONS)
//    public ResponseEntity<String> optionsForSpecificCar() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
//                .build();
//    }

}
