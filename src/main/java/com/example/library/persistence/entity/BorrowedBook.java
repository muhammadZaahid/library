package com.example.library.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBook {

    @Id
    private String id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Member member;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    @PrePersist
    public void generateId() {
        this.id = java.util.UUID.randomUUID().toString();
    }
}
