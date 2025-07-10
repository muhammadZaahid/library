package com.example.library.persistence.repository;

import com.example.library.persistence.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    Page<Member> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
