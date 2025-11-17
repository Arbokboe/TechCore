package com.example.dto;

import com.example.domain.Author;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateBookDto {
    private String title;
    private Author author;
    private int year;

    public CreateBookDto() {
    }

}
