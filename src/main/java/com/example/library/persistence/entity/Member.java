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
public class Member {

    @Id
    private String id;

    private String name;
    private String email;
    private String phone;

    @PrePersist
    public void generateId() {
        this.id = java.util.UUID.randomUUID().toString();
    }
}
