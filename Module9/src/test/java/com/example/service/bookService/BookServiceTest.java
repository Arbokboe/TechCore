package com.example.service.bookService;

import com.example.datasource.mapper.BookMapper;
import com.example.datasource.model.Book;
import com.example.datasource.repository.AuthorRepository;
import com.example.datasource.repository.BookRepository;
import com.example.web.dto.BookResponseDto;
import com.example.web.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository, authorRepository, bookMapper);
    }

    @Test
    void testFindById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");

        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle("Test Book");

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        when(bookMapper.toDto(book))
                .thenReturn(bookResponseDto);

        BookResponseDto result = bookService.findBookById(1L);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookMapper, times(1)).toDto(book);
    }
}
