package com.example.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {
    private String title;
    private String author;
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

}
