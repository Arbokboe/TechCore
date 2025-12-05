package com.example.datasource.repository;

import com.example.datasource.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor_Name(String author);

    Optional<Book> findByTitleAndAuthor_Name(String title, String author);

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthors();

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchText, '%')) ORDER BY b.public_year DESC")
    List<Book> findByPartTitle(@Param("searchText") String searchText);
}
