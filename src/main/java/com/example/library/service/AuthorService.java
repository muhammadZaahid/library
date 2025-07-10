package com.example.library.service;

import com.example.library.model.request.AuthorRequest;
import com.example.library.model.response.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    Page<AuthorResponse> getAll(String inquiry , Pageable pageable);
    AuthorResponse getById(String id);
    AuthorResponse create(AuthorRequest request);
    AuthorResponse update(String id, AuthorRequest request);
    void delete(String id);
    void bulkDelete(List<String> ids);
}
