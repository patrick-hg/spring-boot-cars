package com.pha.spring_boot_cars.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void should_save_a_book() {

        Book book = bookTheLostGardens();

        bookRepository.save(book);

        Assertions.assertTrue(bookRepository.findByIsbn("978-0-312-94932-7").isPresent());
    }

    private Book bookTheLostGardens () {
        Book book = new Book();
        book.setTitle("The lost gardens");
        book.setAuthors("Antony Eglin");
        book.setIsbn("978-0-312-94932-7");
        book.setPrice(6.99F);
        book.setPublisher("Minotaur Books");
        book.setYearPublished(2007);
        return book;
    }

}