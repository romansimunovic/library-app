package com.library.library_backend.repository;

import com.library.library_backend.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing BookEntity data.
 *
 * All queries are based on the public UUID (bookId),
 * not on the database primary key.
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /** Find a book by its public UUID */
    Optional<BookEntity> findByBookId(String bookId);

    /** Search books by title or author (case-insensitive) */
    List<BookEntity> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title,
            String author
    );
}
