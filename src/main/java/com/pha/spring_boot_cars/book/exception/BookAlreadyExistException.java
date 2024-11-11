package com.pha.spring_boot_cars.book.exception;

public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException(String message) {
        super(message);
    }
}
