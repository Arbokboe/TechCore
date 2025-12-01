package com.example.controller;

import com.example.dto.bookDto.BookResponseDto;
import com.example.dto.bookDto.CreateBookDto;
import com.example.domain.Book;
import com.example.mapper.BookMapper;
import com.example.service.AsyncService;
import com.example.service.bookService.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;
    private final AsyncService asyncService;
    private final BookMapper bookMapper;

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/book/all")
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.saveBook(createBookDto));
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody CreateBookDto updateBookDto) {
        return ResponseEntity.ok(bookService.updateBook(id, updateBookDto));
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("book/author/{author}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.findBooksByAuthor_Name(author));
    }

    @GetMapping("book/search")
    public ResponseEntity<BookResponseDto> getBookByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam String author) {
        return ResponseEntity.ok(bookService.findByTitleAndAuthor_Name(title, author));
    }

    @GetMapping("/n-plus-one")
    public ResponseEntity<List<String>> demonstrateNPlusOne() {
        List<Book> books = bookService.getAllBooks();

        List<String> result = new ArrayList<>();
        for (Book book : books) {
            String bookInfo = String.format("Книга: '%s', Автор: '%s'",
                    book.getTitle(), book.getAuthor().getName());
            result.add(bookInfo);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> searchBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
    }

    @PostMapping("/rollback")
    public ResponseEntity<String> createBookWithAuthorAndRollback(@RequestBody CreateBookDto createBookDto) {
        try {
            Book book = bookService.createBookWithAuthorAndRollback(createBookDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Книга создана: " + book.getTitle());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Транзакция откатилась: " + e.getMessage());
        }
    }

    @GetMapping("/long-task")
    public String triggerLongTask() {
        asyncService.performLongRunningTask();
        return "Задача запущена";
    }

    @GetMapping("/page")
    public Page<BookResponseDto> getAllBooksWithPagination(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

}
