package com.example.library.persistence.repository;

import com.example.library.persistence.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    List<Member> findByNameContainingIgnoreCase(String name);
}
