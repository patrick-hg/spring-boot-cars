package com.pha.spring_boot_cars.book;

import com.pha.spring_boot_cars.book.dto.BookDto;
import com.pha.spring_boot_cars.book.dto.BookUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@Slf4j
public class BookController {

    private BookService bookService;
    private BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping(path = "/{isbn}")
    public ResponseEntity<BookDto> getBook(String isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<BookDto>> searchByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.ok(bookService.searchByAuthor(author));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(BookDto bookDto) {
        return ResponseEntity.ok(
                bookService.addBook(bookMapper.toEntity(bookDto)));
    }

    @PutMapping(path = "/{isbn}")
    public ResponseEntity<BookDto> updateBook(String isbn, BookUpdateRequestDto bookUpdateRequestDto) {
        return ResponseEntity.ok(
                bookService.updateBook(isbn, bookUpdateRequestDto));
    }

    @DeleteMapping(path = "/{isbn}")
    public ResponseEntity deleteBook(String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{isbn}")
    public ResponseEntity<String> addBookProhibited(String isbn) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).build();
    }

    @RequestMapping
    public ResponseEntity<String> supportedForBooks() {
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST)
                .build();
    }

    @RequestMapping(path = "/{isbn}")
    public ResponseEntity<String> supportedForBooksIsbn() {
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }

}
