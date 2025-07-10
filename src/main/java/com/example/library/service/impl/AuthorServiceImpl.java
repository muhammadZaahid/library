package com.example.library.service.impl;

import com.example.library.model.request.AuthorRequest;
import com.example.library.model.response.AuthorResponse;
import com.example.library.persistence.entity.Author;
import com.example.library.persistence.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public Page<AuthorResponse> getAll(String inquiry, Pageable pageable) {
        Page<Author> authors;

        if (inquiry != null && !inquiry.trim().isEmpty()) {
            authors = repository.findByNameContainingIgnoreCase(inquiry, pageable);
        } else {
            authors = repository.findAll(pageable);
        }

        return authors.map(this::toResponse);
    }



    @Override
    public AuthorResponse getById(String id) {
        UUID uuid = UUID.fromString(id);
        return repository.findById(uuid)
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
        UUID uuid = UUID.fromString(id);

        Author author = repository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(request.getName());
        repository.save(author);

        return toResponse(author);
    }


    @Override
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        repository.deleteById(uuid);
    }

    @Override
    public void bulkDelete(List<String> ids) {
        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();

        repository.deleteAllById(uuidList);
    }


    private AuthorResponse toResponse(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId().toString());
        response.setName(author.getName());
        return response;
    }
}
