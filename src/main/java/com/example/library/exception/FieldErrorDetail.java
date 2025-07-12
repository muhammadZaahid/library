package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDetail {
    private String field;
    private String message;
}
