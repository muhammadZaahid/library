package com.example.library.controller;

import com.example.library.model.request.AuthorRequest;
import com.example.library.model.response.AuthorResponse;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    public List<AuthorResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public AuthorResponse create(@RequestBody AuthorRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable String id, @RequestBody AuthorRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

