package com.example.library.service.impl;

import com.example.library.model.request.AuthorRequest;
import com.example.library.model.response.AuthorResponse;
import com.example.library.persistence.entity.Author;
import com.example.library.persistence.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public List<AuthorResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public AuthorResponse getById(String id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public AuthorResponse create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        repository.save(author);
        return toResponse(author);
    }

    @Override
    public AuthorResponse update(String id, AuthorRequest request) {
        Author author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(request.getName());
        repository.save(author);

        return toResponse(author);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    private AuthorResponse toResponse(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        return response;
    }
}
