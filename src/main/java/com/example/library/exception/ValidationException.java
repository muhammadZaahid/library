package com.example.library.exception;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<FieldErrorDetail> errors;

    public ValidationException(List<FieldErrorDetail> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<FieldErrorDetail> getErrors() {
        return errors;
    }
}

