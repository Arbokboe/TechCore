package com.example.datasource.mapper;

import com.example.datasource.model.Author;
import com.example.datasource.model.Book;
import com.example.web.dto.BookResponseDto;
import com.example.web.dto.CreateBookDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-10T15:29:47+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(CreateBookDto dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( dto.getTitle() );
        book.setPublicYear( dto.getPublicYear() );

        return book;
    }

    @Override
    public BookResponseDto toDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setAuthorName( bookAuthorName( book ) );
        bookResponseDto.setTitle( book.getTitle() );
        bookResponseDto.setPublicYear( book.getPublicYear() );

        return bookResponseDto;
    }

    private String bookAuthorName(Book book) {
        if ( book == null ) {
            return null;
        }
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        String name = author.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
