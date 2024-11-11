package com.pha.spring_boot_cars.book.exception;

public class BookNotExistException extends RuntimeException {
    public BookNotExistException(String message) {
        super(message);
    }
}
