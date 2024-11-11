package com.pha.spring_boot_cars.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String authors;
    private String publisher;
    private String isbn;
    private Integer yearPublished;
    private Float price;
}
