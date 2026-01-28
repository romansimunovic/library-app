package com.library.library_backend.repository;

import com.library.library_backend.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Repository layer for BookEntity.
 *
 * Uses Spring Data JPA method naming conventions.
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByBookId(String bookId);

    Page<BookEntity> findAll(Pageable pageable);

    List<BookEntity> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title,
            String author
    );
}
