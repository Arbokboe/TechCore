package com.example.dto;


import com.example.domain.Book;
import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private Integer year;
    private String authorName;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.year = book.getYear();
        this.authorName = book.getAuthor().getName();
    }
}
