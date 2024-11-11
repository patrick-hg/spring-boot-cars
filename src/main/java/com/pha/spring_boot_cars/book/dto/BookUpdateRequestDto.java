package com.pha.spring_boot_cars.book.dto;

import lombok.Builder;

@Builder
public record BookUpdateRequestDto(
        String title,
        String authors,
        String publisher,
        String isbn,
        Integer yearPublished,
        Float price) {
}
