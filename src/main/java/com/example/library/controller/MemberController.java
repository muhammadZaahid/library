package com.example.library.controller;

import com.example.library.model.request.MemberRequest;
import com.example.library.model.response.MemberResponse;
import com.example.library.persistence.entity.Member;
import com.example.library.persistence.repository.MemberRepository;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping
    public List<MemberResponse> getAll(@RequestParam(required = false) String inquiry) {
        return service.getAll(inquiry);
    }

    @GetMapping("/{id}")
    public MemberResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public MemberResponse create(@RequestBody MemberRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public MemberResponse update(@PathVariable String id, @RequestBody MemberRequest request) {
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

