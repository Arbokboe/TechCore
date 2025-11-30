package com.example.dto.bookDto;


import com.example.domain.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
