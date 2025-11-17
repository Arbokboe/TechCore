package com.example.repository;

import com.example.domain.Author;
import com.example.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(Author author);

    Optional<Book> findByTitleAndAuthor(String title, Author author);

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthors();

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchText, '%')) ORDER BY b.year DESC")
    List<Book> findByTitleContainingIgnoreCaseOrderByYearDesc(@Param("searchText") String searchText);
}
