package com.pha.spring_boot_cars.book.exception;

public class BookIllegalUpdateException extends RuntimeException {

    public BookIllegalUpdateException(String message) {
        super(message);
    }
}
