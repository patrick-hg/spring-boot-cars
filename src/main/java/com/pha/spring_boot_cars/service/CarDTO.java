package com.pha.spring_boot_cars.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarDTO implements Serializable {
    private String brandName;
    private String modelName;
    private String registrationNumber;
    private String carType;
    private int year;
    private int kms;
    private float price;
}
