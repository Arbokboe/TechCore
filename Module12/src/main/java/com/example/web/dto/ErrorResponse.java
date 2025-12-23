package com.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String error;
    private int status;
}
