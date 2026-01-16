package com.library.library_backend.repository;

import com.library.library_backend.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository sloj za rad s entitetom {@link BookEntity}.
 * <p>
 * Nasljeđuje {@link JpaRepository} što omogućuje korištenje gotovih CRUD operacija:
 * - findAll()
 * - findById(Long id)
 * - save(BookEntity entity)
 * - delete(BookEntity entity)
 * - itd.
 * <p>
 * Također definira dodatnu metodu za dohvat knjige po jedinstvenom {@code bookId} koji se koristi u REST API.
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /**
     * Dohvaća knjigu prema jedinstvenom {@code bookId}.
     *
     * @param bookId Jedinstveni UUID knjige koji se koristi u REST API.
     * @return {@link Optional} s {@link BookEntity} ako knjiga postoji, prazno {@link Optional} ako ne postoji.
     */
    Optional<BookEntity> findByBookId(String bookId);
}
