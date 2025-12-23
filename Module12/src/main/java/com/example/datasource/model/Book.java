package com.example.datasource.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "author_id")
    private Author author;

    private int publicYear;

    public Book() {
    }

    public Book(String title, Author author, int publicYear) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
    }

    public Book(Long id, String title, Author author, int publicYear) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
        this.id = id;
    }
}
