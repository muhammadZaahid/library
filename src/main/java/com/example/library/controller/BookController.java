package com.example.library.controller;

import com.example.library.model.request.BookRequest;
import com.example.library.model.response.BookResponse;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<BookResponse> getAll(@RequestParam(required = false) String inquiry) {
        return service.getAll(inquiry);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public BookResponse create(@RequestBody BookRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable String id, @RequestBody BookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}


