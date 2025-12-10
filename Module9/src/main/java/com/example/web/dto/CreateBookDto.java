package com.example.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBookDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
    @NotBlank
    private String authorName;
    @Min(1900)
    private int publicYear;

    public CreateBookDto() {
    }
}


//        {
//        "title":"new book",
//        "authorName":"author",
//        "publicYear":"2025"
//        }