package com.example.library.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private String category;
    private int publishingYear;
    private String authorId;
}
