package com.example.library.service.impl;

import com.example.library.model.request.BorrowedBookRequest;
import com.example.library.model.response.BorrowedBookResponse;
import com.example.library.persistence.entity.Book;
import com.example.library.persistence.entity.BorrowedBook;
import com.example.library.persistence.entity.Member;
import com.example.library.persistence.repository.BookRepository;
import com.example.library.persistence.repository.BorrowedBookRepository;
import com.example.library.persistence.repository.MemberRepository;
import com.example.library.service.BorrowedBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowedBookServiceImpl implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepo;
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;

    @Override
    public List<BorrowedBookResponse> getAll() {
        return borrowedBookRepo.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public BorrowedBookResponse getById(String id) {
        return borrowedBookRepo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("BorrowedBook not found"));
    }

    @Override
    public BorrowedBookResponse create(BorrowedBookRequest request) {
        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepo.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        BorrowedBook borrowed = new BorrowedBook();
        borrowed.setBook(book);
        borrowed.setMember(member);
        borrowed.setBorrowDate(request.getBorrowDate());
        borrowed.setReturnDate(request.getReturnDate());

        borrowedBookRepo.save(borrowed);
        return toResponse(borrowed);
    }

    @Override
    public BorrowedBookResponse update(String id, BorrowedBookRequest request) {
        BorrowedBook borrowed = borrowedBookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("BorrowedBook not found"));

        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepo.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        borrowed.setBook(book);
        borrowed.setMember(member);
        borrowed.setBorrowDate(request.getBorrowDate());
        borrowed.setReturnDate(request.getReturnDate());

        borrowedBookRepo.save(borrowed);
        return toResponse(borrowed);
    }

    @Override
    public void delete(String id) {
        borrowedBookRepo.deleteById(id);
    }

    private BorrowedBookResponse toResponse(BorrowedBook borrowed) {
        BorrowedBookResponse res = new BorrowedBookResponse();
        res.setId(borrowed.getId());
        res.setBookId(borrowed.getBook().getId());
        res.setBookTitle(borrowed.getBook().getTitle());
        res.setMemberId(borrowed.getMember().getId());
        res.setMemberName(borrowed.getMember().getName());
        res.setBorrowDate(borrowed.getBorrowDate());
        res.setReturnDate(borrowed.getReturnDate());
        return res;
    }
}
