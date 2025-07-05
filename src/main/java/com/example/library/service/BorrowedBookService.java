package com.example.library.service;

import com.example.library.model.request.BorrowedBookRequest;
import com.example.library.model.response.BorrowedBookResponse;

import java.util.List;

public interface BorrowedBookService {
    List<BorrowedBookResponse> getAll();
    BorrowedBookResponse getById(String id);
    BorrowedBookResponse create(BorrowedBookRequest request);
    BorrowedBookResponse update(String id, BorrowedBookRequest request);
    void delete(String id);
}
