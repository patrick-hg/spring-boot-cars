package com.pha.spring_boot_cars.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
    Boolean existsByIsbn(String isbn);

    List<Book> findByAuthors(String authors);
}
