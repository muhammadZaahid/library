package com.example.library.model.request;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookRequest {
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
