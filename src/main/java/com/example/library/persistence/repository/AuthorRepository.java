package com.example.library.persistence.repository;

import com.example.library.persistence.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Page<Author> findByNameContainingIgnoreCase(String name, Pageable pageable);
    boolean existsByEmail(String email);
}