package com.example.web.service;

import com.example.datasource.model.Book;
import com.example.web.dto.BookResponseDto;
import com.example.web.dto.CreateBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    BookResponseDto saveBook(CreateBookDto createBookDto);

    void deleteBook(Long id);

    BookResponseDto findBookById(Long id);

    Page<BookResponseDto> getAllBooks(Pageable pageable);

    List<Book> getAllBooks();

    BookResponseDto updateBook(Long id, CreateBookDto updateBookDto);

    List<BookResponseDto> findBooksByAuthor_Name(String author);

    BookResponseDto findByTitleAndAuthor_Name(String title, String author);

    List<BookResponseDto> searchBooksByTitle(String searchText);

    Book createBookWithAuthorAndRollback(CreateBookDto dto);
}
