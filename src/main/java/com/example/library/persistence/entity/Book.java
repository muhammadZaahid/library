package com.example.library.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String id;

    private String title;
    private String category;
    private int publishingYear;

    @ManyToOne
    private Author author;

    @PrePersist
    public void generateId() {
        this.id = java.util.UUID.randomUUID().toString();
    }
}