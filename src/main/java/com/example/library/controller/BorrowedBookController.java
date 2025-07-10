package com.example.library.controller;

import com.example.library.model.request.BorrowedBookRequest;
import com.example.library.model.response.BorrowedBookResponse;
import com.example.library.model.response.PagedResponse;
import com.example.library.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed-books")
@CrossOrigin(origins = "http://localhost:3000")
public class BorrowedBookController {

    @Autowired
    private BorrowedBookService service;

    @GetMapping
    public PagedResponse<BorrowedBookResponse> getAll(
            @RequestParam(required = false) String inquiry,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
            ) {
        int pageNumber = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? size : Integer.MAX_VALUE;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("memberName").ascending());
        Page<BorrowedBookResponse> borrowedPage = service.getAll(inquiry, pageable);
        return new PagedResponse<>(borrowedPage);
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

    @PostMapping("/bulk-delete")
    public void bulkDelete(@RequestBody List<String> ids) {
        service.bulkDelete(ids);
    }
}

