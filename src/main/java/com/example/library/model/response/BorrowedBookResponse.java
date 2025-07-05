package com.example.library.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookResponse {
    private String id;
    private String bookId;
    private String bookTitle;
    private String memberId;
    private String memberName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}