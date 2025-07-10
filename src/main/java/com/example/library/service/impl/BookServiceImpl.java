package com.example.library.service.impl;

import com.example.library.model.request.BookRequest;
import com.example.library.model.response.BookResponse;
import com.example.library.persistence.entity.Author;
import com.example.library.persistence.entity.Book;
import com.example.library.persistence.repository.AuthorRepository;
import com.example.library.persistence.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;

    private final AuthorRepository authorRepo;

    @Override
    public Page<BookResponse> getAll(String inquiry, Pageable pageable) {
        Page<Book> books;

        if (inquiry != null && !inquiry.trim().isEmpty()) {
            books = bookRepo.findByTitleContainingIgnoreCase(inquiry, pageable);
        } else {
            books = bookRepo.findAll(pageable);
        }

        return books.map(this::toResponse);
    }

    @Override
    public BookResponse getById(String id) {

        return bookRepo.findById(UUID.fromString(id))
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public BookResponse create(BookRequest request) {
        UUID uuid = UUID.fromString(request.getAuthorId());
        Author author = authorRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setPublishingYear(request.getPublishingYear());
        book.setAuthor(author);

        bookRepo.save(book);
        return toResponse(book);
    }

    @Override
    public BookResponse update(String id, BookRequest request) {
        Book book = bookRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Book not found"));
        UUID uuid = UUID.fromString(request.getAuthorId());
        Author author = authorRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setPublishingYear(request.getPublishingYear());
        book.setAuthor(author);

        bookRepo.save(book);
        return toResponse(book);
    }

    @Override
    public void delete(String id) {
        bookRepo.deleteById(UUID.fromString(id));
    }

    @Override
    public void bulkDelete(List<String> ids) {
        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();

        bookRepo.deleteAllById(uuidList);
    }

    private BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId().toString());
        response.setTitle(book.getTitle());
        response.setCategory(book.getCategory());
        response.setPublishingYear(book.getPublishingYear());
        response.setAuthorId(book.getAuthor().getId().toString());
        response.setAuthorName(book.getAuthor().getName());
        return response;
    }
}
