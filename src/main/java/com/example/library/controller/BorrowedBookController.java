package com.example.library.controller;

import com.example.library.model.request.BorrowedBookRequest;
import com.example.library.model.response.BorrowedBookResponse;
import com.example.library.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed-books")
@CrossOrigin(origins = "http://localhost:3000")
public class BorrowedBookController {

    @Autowired
    private BorrowedBookService service;

    @GetMapping
    public List<BorrowedBookResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BorrowedBookResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public BorrowedBookResponse create(@RequestBody BorrowedBookRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BorrowedBookResponse update(@PathVariable String id, @RequestBody BorrowedBookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

