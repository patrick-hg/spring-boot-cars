package com.pha.spring_boot_cars.book;

import com.pha.spring_boot_cars.book.dto.BookDto;
import com.pha.spring_boot_cars.book.dto.BookUpdateRequestDto;
import com.pha.spring_boot_cars.book.exception.BookAlreadyExistException;
import com.pha.spring_boot_cars.book.exception.BookIllegalUpdateException;
import com.pha.spring_boot_cars.book.exception.BookNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getById(Long id) {
        return bookMapper.toDto(bookRepository.getReferenceById(id));
    }

    public BookDto findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotExistException("Failed to find a book with ISBN:" + isbn));
    }

    public List<BookDto> searchByAuthor(String author) {
        return bookRepository.findByAuthors(author)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean existByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }

    public BookDto addBook(Book book) {
        log.info("Adding a Book : {}", book.toString());
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistException("Book (isbn:%s) already exist".formatted(book.getIsbn()));
        }
        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookDto updateBook(String isbn, BookUpdateRequestDto bookUpdateRequest) {
        if (!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotExistException("Book (isbn:%s) you are trying to update does not exist".formatted(isbn));
        }
        if (bookUpdateRequest.isbn() != null && !Objects.equals(isbn, bookUpdateRequest.isbn())) {
            throw new BookIllegalUpdateException("Changing the ISBN value of a book is not an authorized operation");
        }
        Book book = bookRepository.findByIsbn(isbn)
                .map(b -> updateBookLambda.apply(bookUpdateRequest, b))
                .orElseThrow();
        return bookMapper.toDto(bookRepository.save(book));
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotExistException("The book (isbn:%s) you are trying to delete does not exist".formatted(isbn)));
        bookRepository.delete(book);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    private final BiFunction<BookUpdateRequestDto, Book, Book> updateBookLambda = (bookUpdateRequest, book) -> {
        if (bookUpdateRequest.title() != null)
            book.setTitle(bookUpdateRequest.title());
        if (bookUpdateRequest.authors() != null)
            book.setAuthors(bookUpdateRequest.authors());
        if (bookUpdateRequest.price() != null)
            book.setPrice(bookUpdateRequest.price());
        return book;
    };
}
