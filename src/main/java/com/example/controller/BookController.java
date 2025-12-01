package com.example.controller;

import com.example.domain.Author;
import com.example.dto.BookResponseDto;
import com.example.dto.CreateBookDto;
import com.example.domain.Book;
import com.example.service.BookService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Data
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("books/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.saveBook(bookService.createBookFromDto(createBookDto)));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody CreateBookDto updateBookDto) {
        return ResponseEntity.ok(bookService.updateBook(id, updateBookDto));
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("books/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Author author) {
        return ResponseEntity.ok(bookService.findBooksByAuthor(author));
    }

    @GetMapping("books/search")
    public ResponseEntity<Book> getBookByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam Author author) {
        return ResponseEntity.ok(bookService.findBookByTitleAndAuthor(title, author));
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

}
