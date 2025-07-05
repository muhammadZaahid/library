package com.example.library.service;

import com.example.library.model.request.MemberRequest;
import com.example.library.model.response.MemberResponse;

import java.util.List;

public interface MemberService {
    List<MemberResponse> getAll();
    MemberResponse getById(String id);
    MemberResponse create(MemberRequest request);
    MemberResponse update(String id, MemberRequest request);
    void delete(String id);
}
