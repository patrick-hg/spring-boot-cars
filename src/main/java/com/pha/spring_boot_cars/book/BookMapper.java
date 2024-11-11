package com.pha.spring_boot_cars.book;

import com.pha.spring_boot_cars.book.dto.BookDto;
import com.pha.spring_boot_cars.commons.CommonMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements CommonMapper<Book, BookDto> {

    @Override
    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthors(dto.getAuthors());
        book.setIsbn(dto.getIsbn());
        book.setPublisher(dto.getPublisher());
        book.setYearPublished(dto.getYearPublished());
        book.setPrice(dto.getPrice());
        return book;
    }

    @Override
    public BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setTitle(entity.getTitle());
        dto.setAuthors(entity.getAuthors());
        dto.setIsbn(entity.getIsbn());
        dto.setPublisher(entity.getPublisher());
        dto.setYearPublished(entity.getYearPublished());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
