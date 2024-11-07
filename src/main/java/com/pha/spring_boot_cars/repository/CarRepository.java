package com.pha.spring_boot_cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByRegistrationNumber(String registrationNumber);

    Optional<Car> findByRegistrationNumber(String registrationNumber);
}
