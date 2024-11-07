package com.pha.spring_boot_cars.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class DetailledErrorResponse {

    private Date date;
    private String message;
    private String description;
}
