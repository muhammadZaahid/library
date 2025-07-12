package com.example.library.model.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {
    private String name;
    private String email;
    private String bio;
}
