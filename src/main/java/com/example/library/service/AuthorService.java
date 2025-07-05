package com.example.library.service;

import com.example.library.model.request.AuthorRequest;
import com.example.library.model.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    List<AuthorResponse> getAll();
    AuthorResponse getById(String id);
    AuthorResponse create(AuthorRequest request);
    AuthorResponse update(String id, AuthorRequest request);
    void delete(String id);
}
