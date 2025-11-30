package com.example.service.BookService;

import com.example.domain.Book;
import com.example.dto.bookDto.BookResponseDto;
import com.example.dto.bookDto.CreateBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
