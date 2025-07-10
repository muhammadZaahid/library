package com.example.library.service;

import com.example.library.model.request.BorrowedBookRequest;
import com.example.library.model.response.BorrowedBookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BorrowedBookService {
    Page<BorrowedBookResponse> getAll(String inquiry, Pageable pageable);
    BorrowedBookResponse getById(String id);
    BorrowedBookResponse create(BorrowedBookRequest request);
    BorrowedBookResponse update(String id, BorrowedBookRequest request);
    void delete(String id);
    void bulkDelete(List<String> ids);
}
