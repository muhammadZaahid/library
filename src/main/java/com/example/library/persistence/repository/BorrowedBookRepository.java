package com.example.library.persistence.repository;

import com.example.library.persistence.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, UUID> {

    @Query("""
                SELECT bb FROM BorrowedBook bb
                WHERE LOWER(bb.book.title) LIKE LOWER(CONCAT('%', :inquiry, '%'))
                   OR LOWER(bb.member.name) LIKE LOWER(CONCAT('%', :inquiry, '%'))
            """)
    List<BorrowedBook> findByBookOrMember(@Param("inquiry") String inquiry);

}
