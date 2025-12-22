package com.example.web.service;

import com.example.datasource.model.Author;
import com.example.web.dto.BookResponseDto;
import com.example.web.dto.CreateBookDto;
import com.example.datasource.model.Book;
import com.example.exception.customException.AuthorNotFoundException;
import com.example.exception.customException.BookNotFoundException;
import com.example.datasource.mapper.BookMapper;
import com.example.datasource.repository.AuthorRepository;
import com.example.datasource.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookResponseDto saveBook(CreateBookDto dto) {
        Author author = authorRepository.findByName(dto.getAuthorName()).orElse(new Author(dto.getAuthorName()));
        Book book = bookMapper.toEntity(dto);
        book.setAuthor(author);
        return bookMapper.toDto(bookRepository.save(book));
    }


    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public BookResponseDto findBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found")));
    }

    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(BookResponseDto::new);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookResponseDto updateBook(Long id, CreateBookDto updateBookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        existingBook.setTitle(updateBookDto.getTitle());
        existingBook.setPublicYear(updateBookDto.getPublicYear());

        if (updateBookDto.getAuthorName() != null && !updateBookDto.getAuthorName().isBlank()) {
            Author author = authorRepository.findByName(updateBookDto.getAuthorName())
                    .orElseThrow(() -> new AuthorNotFoundException("Author with name '" + updateBookDto.getAuthorName() + "' not found"));
            existingBook.setAuthor(author);
        }

        return bookMapper.toDto(bookRepository.save(existingBook));
    }

    public List<BookResponseDto> findBooksByAuthor_Name(String author) {
        return bookRepository.findByAuthor_Name(author)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto findByTitleAndAuthor_Name(String title, String author) {
        return bookMapper.toDto(bookRepository.findByTitleAndAuthor_Name(title, author)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book with title '" + title + "' and author '" + author + "' not found")));
    }

    public List<BookResponseDto> searchBooksByTitle(String searchText) {
        List<Book> books = bookRepository.findByPartTitle(searchText);
        return books.stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }
}
