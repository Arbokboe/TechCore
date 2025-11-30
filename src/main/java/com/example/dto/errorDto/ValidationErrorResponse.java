package com.example.dto.errorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ValidationErrorResponse {
    private List<FieldError> errors;
}
