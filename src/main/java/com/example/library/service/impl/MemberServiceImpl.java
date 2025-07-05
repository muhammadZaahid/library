package com.example.library.service.impl;

import com.example.library.model.request.MemberRequest;
import com.example.library.model.response.MemberResponse;
import com.example.library.persistence.entity.Member;
import com.example.library.persistence.repository.MemberRepository;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public List<MemberResponse> getAll() {
        return memberRepo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public MemberResponse getById(String id) {
        return memberRepo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Override
    public MemberResponse create(MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());

        memberRepo.save(member);
        return toResponse(member);
    }

    @Override
    public MemberResponse update(String id, MemberRequest request) {
        Member member = memberRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());

        memberRepo.save(member);
        return toResponse(member);
    }

    @Override
    public void delete(String id) {
        memberRepo.deleteById(id);
    }

    private MemberResponse toResponse(Member member) {
        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        return response;
    }
}
