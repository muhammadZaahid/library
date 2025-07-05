package com.example.library.persistence.repository;

import com.example.library.persistence.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
