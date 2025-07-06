package com.example.library.persistence.repository;

import com.example.library.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findByNameContainingIgnoreCase(String name);
}