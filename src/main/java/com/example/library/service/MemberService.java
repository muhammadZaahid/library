package com.example.library.service;

import com.example.library.model.request.MemberRequest;
import com.example.library.model.response.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    Page<MemberResponse> getAll(String inquiry, Pageable pageable);
    MemberResponse getById(String id);
    MemberResponse create(MemberRequest request);
    MemberResponse update(String id, MemberRequest request);
    void delete(String id);
    void bulkDelete(List<String> ids);
}
