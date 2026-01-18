package com.library.library_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entitet koji predstavlja knjigu u bazi podataka.
 * <p>
 * Polja ovog entiteta se mapiraju na stupce u tablici "book_entity".
 * Koristi se za pohranu i dohvat podataka o knjigama.
 */
@Entity
@Table(name = "book_entity")
public class BookEntity {

    /** Interni ID entiteta (primarni ključ baze, automatski generiran) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Jedinstveni identifikator knjige koji se koristi u REST API (UUID) */
    @Column(unique = true, nullable = false)
    private String bookId = UUID.randomUUID().toString();

    /** Naslov knjige */
    private String title;

    /** Autor knjige */
    private String author;

    /** ISBN knjige */
    private String isbn;

    /** Godina izdavanja knjige */
    private Integer publishedYear; // nullable

    /** Dostupnost knjige u biblioteci */
    private Boolean available;     // nullable

    /** Vrijeme kreiranja zapisa */
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Vrijeme posljednje izmjene zapisa */
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // --- Getteri i setteri ---

    public Long getId() { return id; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublishedYear() { return publishedYear; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }

    public boolean getAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getModifiedAt() { return modifiedAt; }
    public void setModifiedAt(LocalDateTime modifiedAt) { this.modifiedAt = modifiedAt; }
}
