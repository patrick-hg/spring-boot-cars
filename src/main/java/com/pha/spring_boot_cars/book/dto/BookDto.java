package com.pha.spring_boot_cars.book.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDto implements Serializable {
    private String title;
    private String authors;
    private String publisher;
    private String isbn;
    private Integer yearPublished;
    private Float price;
}
