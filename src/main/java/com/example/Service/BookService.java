package com.example.service;

import com.example.dto.CreateBookDto;
import com.example.domain.Book;
import com.example.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {


    private final Map<String, Book> bookStorage = new HashMap<>();

    public BookService() {
        bookStorage.put("1", new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        bookStorage.put("2", new Book("1984", "George Orwell", 1949));
        bookStorage.put("3", new Book("The Hobbit", "J.R.R. Tolkien", 1937));
    }

    public Book createBookFromDto(CreateBookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getPublicationYear() != null ? dto.getPublicationYear() : 2024);
        return book;
    }

    public boolean deleteBook(String id) {
        if (!bookStorage.containsKey(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookStorage.remove(id);
        return true;
    }

    public Book findBookById(String id) {
        Book book = bookStorage.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        return book;
    }
}
