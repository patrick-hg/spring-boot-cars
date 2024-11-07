package com.pha.spring_boot_cars.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "model_name")
    private String modelName;

    @Column(name = "reg_no")
    private String registrationNumber;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "production_year")    // as year name is prohibited
    private int year;

    private int kms;
    private float price;

}
