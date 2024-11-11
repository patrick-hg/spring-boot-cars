package com.pha.spring_boot_cars.car.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarDto implements Serializable {
    private String brandName;
    private String modelName;
    private String registrationNumber;
    private String carType;
    private int year;
    private int kms;
    private float price;
}
