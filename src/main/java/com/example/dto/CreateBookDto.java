package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateBookDto {
    private String title;
    private String author;
    private Integer publicationYear;

    public CreateBookDto() {
    }

}
