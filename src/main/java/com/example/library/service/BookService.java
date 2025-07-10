package com.example.library.service;

import com.example.library.model.request.BookRequest;
import com.example.library.model.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<BookResponse> getAll(String inquiry, Pageable pageable);
    BookResponse getById(String id);
    BookResponse create(BookRequest request);
    BookResponse update(String id, BookRequest request);
    void delete(String id);
    void bulkDelete(List<String> ids);
}
