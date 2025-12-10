package com.example.web.dto;


import com.example.datasource.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookResponseDto {
    private String title;
    private int public_year;
    private String authorName;

    public BookResponseDto(){};

    public BookResponseDto(Book book) {
        this.title = book.getTitle();
        this.authorName = book.getAuthor().getName();
        this.public_year = book.getPublic_year();
    }
}
