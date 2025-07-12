package com.example.library.model.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private UUID id;
    private String name;
    private String email;
    private String bio;
}
