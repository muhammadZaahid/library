package com.example.library.service;

import com.example.library.model.request.BookRequest;
import com.example.library.model.response.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getAll();
    BookResponse getById(String id);
    BookResponse create(BookRequest request);
    BookResponse update(String id, BookRequest request);
    void delete(String id);
}
