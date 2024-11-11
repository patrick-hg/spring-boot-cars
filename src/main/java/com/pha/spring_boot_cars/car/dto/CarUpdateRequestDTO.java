package com.pha.spring_boot_cars.car.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarUpdateRequestDTO implements Serializable {
    private int kms;
    private float price;
}
