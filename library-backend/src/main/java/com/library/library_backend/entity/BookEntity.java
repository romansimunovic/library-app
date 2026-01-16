package com.library.library_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entitet koji predstavlja knjigu u bazi.
 * bookId je jedinstveni identifikator (UUID) koji se koristi u REST API.
 */
@Entity
@Table(name = "book_entity")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String bookId = UUID.randomUUID().toString();

    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private boolean available;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // --- Getters i Setters ---

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
