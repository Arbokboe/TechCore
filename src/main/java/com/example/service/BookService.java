package com.example.service;

import com.example.domain.Author;
import com.example.dto.BookResponseDto;
import com.example.dto.CreateBookDto;
import com.example.domain.Book;
import com.example.exception.BookNotFoundException;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book createBookFromDto(CreateBookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());

        Author author = authorRepository.findByName(dto.getAuthor().getName())
                .orElseGet(() -> {
                    Author newAuthor = new Author(dto.getAuthor().getName());
                    return authorRepository.save(newAuthor);
                });
        author.setName(dto.getAuthor().getName());
        book.setAuthor(author);
        return book;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllWithAuthors();
    }

    public Book updateBook(Long id, CreateBookDto updateBookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        existingBook.setTitle(updateBookDto.getTitle());
        existingBook.setAuthor(updateBookDto.getAuthor());
        existingBook.setYear(updateBookDto.getYear());

        return bookRepository.save(existingBook);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public Book findBookByTitleAndAuthor(String title, Author author) {
        return bookRepository.findByTitleAndAuthor(title, author)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book with title '" + title + "' and author '" + author + "' not found"));
    }

    public List<BookResponseDto> searchBooksByTitle(String searchText) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCaseOrderByYearDesc(searchText);
        return books.stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Book createBookWithAuthorAndRollback(CreateBookDto dto) {
        Author author = new Author();
        author.setName(dto.getAuthor().getName());
        Author savedAuthor = authorRepository.save(author);

        throw new RuntimeException("Искусственная ошибка для демонстрации отката транзакции!");

//         Book book = new Book();
//         book.setTitle(dto.getTitle());
//         book.setYear(dto.getYear());
//         book.setAuthor(savedAuthor);
//         return bookRepository.save(book);
    }

}
