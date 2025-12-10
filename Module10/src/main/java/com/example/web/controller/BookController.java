package com.example.web.controller;

import com.example.web.dto.BookResponseDto;
import com.example.web.dto.CreateBookDto;
import com.example.datasource.model.Book;
import com.example.datasource.mapper.BookMapper;
import com.example.web.service.AsyncService;
import com.example.web.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AsyncService asyncService;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.saveBook(createBookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody CreateBookDto updateBookDto) {
        return ResponseEntity.ok(bookService.updateBook(id, updateBookDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.findBooksByAuthor_Name(author));
    }

    @GetMapping("/searchByTitleAndAuthor")
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
