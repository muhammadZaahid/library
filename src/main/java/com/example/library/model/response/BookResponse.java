package com.example.library.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String id;
    private String title;
    private String category;
    private int publishingYear;
    private String authorId;
    private String authorName;
}
