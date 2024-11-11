package com.pha.spring_boot_cars.book;

import com.pha.spring_boot_cars.book.dto.BookDto;
import com.pha.spring_boot_cars.book.dto.BookUpdateRequestDto;
import com.pha.spring_boot_cars.book.exception.BookAlreadyExistException;
import com.pha.spring_boot_cars.book.exception.BookIllegalUpdateException;
import com.pha.spring_boot_cars.book.exception.BookNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BooksIntegrationTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookController bookController;

    @Test
    void should_run_assignement_scenario() {
        // empty database
        assertTrue(bookController.getAllBooks().getBody().isEmpty());

        // a. add a book
        BookDto theLostGardens = bookTheLostGardens();
        assertEquals(HttpStatus.OK, bookController.createBook(theLostGardens).getStatusCode());

        // b. add the exact same book again - an exception
        assertThrows(BookAlreadyExistException.class, () -> bookController.createBook(theLostGardens));

        // c. add a second (different) book
        BookDto stPancrasStation = bookStPancrasStation();
        assertEquals(HttpStatus.OK, bookController.createBook(stPancrasStation).getStatusCode());

        // d. retrieve all books
        List<BookDto> allBooks = bookController.getAllBooks().getBody();
        assertEquals(2, allBooks.size());

        // e. retrieve one book by ISBN
        assertEquals(theLostGardens, bookController.getBook(theLostGardens.getIsbn()));
        assertEquals(stPancrasStation, bookController.getBook(stPancrasStation.getIsbn()));

        // f. retrieve a book using an invalid ISBN - an exception
        assertThrows(BookNotExistException.class, () -> bookService.findByIsbn("000-0-0000-00"));

        // g. remove one book
        bookController.deleteBook(stPancrasStation.getIsbn());
        assertFalse(bookService.existByIsbn(stPancrasStation.getIsbn()));

        // h. remove a book using an invalid ISBN
        assertThrows(BookNotExistException.class, () -> bookController.deleteBook("000-0-0000-00"));

        // i. remove all books
        bookService.deleteAllBooks();
        assertTrue(bookService.getAllBooks().isEmpty());

        // re-create the books
        bookController.createBook(theLostGardens);
        bookController.createBook(stPancrasStation);
        bookController.createBook(bookTheBlueRose());

        // j. change book title
        bookController.updateBook(
                stPancrasStation.getIsbn(),
                BookUpdateRequestDto.builder().title("Saint Pancras Station").build());
        assertEquals("Saint Pancras Station",
                bookController.getBook(stPancrasStation.getIsbn()).getBody().getTitle());

        // k. try to update a book when the ISBN in the URI is not the same as the ISBN in the entity body - an exception
        BookUpdateRequestDto bookUpdateRequestDto = BookUpdateRequestDto.builder().isbn("000-0-0000-00").build();
        assertThrows(BookIllegalUpdateException.class,
                () -> bookController.updateBook(stPancrasStation.getIsbn(), bookUpdateRequestDto));

        // l. using the query/request parameter “author”, retrieve all books authored by “Anthony Eglin”
        List<BookDto> booksFromAnthonyEglin = bookController.searchByAuthor("Anthony Eglin").getBody();
        Assertions.assertEquals(2, booksFromAnthonyEglin.size());

        // m. try to add a book using the URI /books/{isbn} - HTTP method not allowed
//        assertThrows(HttpClientErrorException.MethodNotAllowed.class, bookController.)
    }

    private BookDto bookTheLostGardens () {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("The lost gardens");
        bookDto.setAuthors("Anthony Eglin");
        bookDto.setIsbn("978-0-312-94932-7");
        bookDto.setPrice(6.99F);
        bookDto.setPublisher("Minotaur Books");
        bookDto.setYearPublished(2007);
        return bookDto;
    }

    private BookDto bookStPancrasStation () {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("St Pancras Station");
        bookDto.setAuthors("Simon Bradley");
        bookDto.setIsbn("978-1-86197-951-3");
        bookDto.setPrice(8.99F);
        bookDto.setPublisher("Profile Books");
        bookDto.setYearPublished(1996);
        return bookDto;
    }

    private BookDto bookTheBlueRose () {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("The Blue Rose");
        bookDto.setAuthors("Anthony Eglin");
        bookDto.setIsbn("978-1250005335");
        bookDto.setPrice(23.19F);
        bookDto.setPublisher("Minotaur Books");
        bookDto.setYearPublished(2005);
        return bookDto;
    }

}
