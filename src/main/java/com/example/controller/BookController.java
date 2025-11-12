package com.example.controller;

import com.example.dto.CreateBookDto;
import com.example.domain.Book;
import com.example.service.BookService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*

 Exercise 7

 1. Put - обновляет ресурс полностью и клиент должен отправить все поля, даже если изменяется только одно,
 если какие то поля не будут отправлены, они сбросятся до значения по умолчанию, так же Put методы являются
 идемпотентными, т.е. многократный вызов должен вернуть один и тот же результат

 2. Patch - обновляет ресурс частично, клиент отправляет только изменяемые поля, Patch методы неидемпотентны

 3. Поэтому при реализации Patch метода нужно обновлять только те данные которые были получены от клиента,
 остальные должны оставаться такими же

 */


@Data
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public Book getBook() {
        return new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable String id) {
        return "Book with ID: " + id;
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam(required = false) String title) {
        if (title == null) {
            return "Searching all books";
        }
        return "Searching books with title: " + title;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto createBookDto) {
        Book book = bookService.createBookFromDto(createBookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody CreateBookDto updateBookDto) {
        Book updatedBook = bookService.createBookFromDto(updateBookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
