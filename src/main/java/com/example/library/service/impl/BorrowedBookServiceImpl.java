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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BorrowedBookServiceImpl implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepo;
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;

    @Override
    public Page<BorrowedBookResponse> getAll(String inquiry, Pageable pageable) {
        Page<BorrowedBook> borrowedBooks;
        if (inquiry != null && !inquiry.trim().isEmpty()) {
            borrowedBooks = borrowedBookRepo.findByBookOrMember(inquiry, pageable);
        } else {
            borrowedBooks = borrowedBookRepo.findAll(pageable);
        }

        return borrowedBooks.map(this::toResponse);
    }

    @Override
    public BorrowedBookResponse getById(String id) {
        return borrowedBookRepo.findById(UUID.fromString(id))
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("BorrowedBook not found"));
    }

    @Override
    public BorrowedBookResponse create(BorrowedBookRequest request) {
        Book book = bookRepo.findById(UUID.fromString(request.getBookId()))
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepo.findById(UUID.fromString(request.getMemberId()))
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
        BorrowedBook borrowed = borrowedBookRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("BorrowedBook not found"));

        Book book = bookRepo.findById(UUID.fromString(request.getBookId()))
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepo.findById(UUID.fromString(request.getMemberId()))
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
        borrowedBookRepo.deleteById(UUID.fromString(id));
    }

    @Override
    public void bulkDelete(List<String> ids) {
        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();

        borrowedBookRepo.deleteAllById(uuidList);
    }

    private BorrowedBookResponse toResponse(BorrowedBook borrowed) {
        BorrowedBookResponse res = new BorrowedBookResponse();
        res.setId(borrowed.getId().toString());
        res.setBookId(borrowed.getBook().getId().toString());
        res.setBookTitle(borrowed.getBook().getTitle());
        res.setMemberId(borrowed.getMember().getId().toString());
        res.setMemberName(borrowed.getMember().getName());
        res.setBorrowDate(borrowed.getBorrowDate());
        res.setReturnDate(borrowed.getReturnDate());
        return res;
    }
}
