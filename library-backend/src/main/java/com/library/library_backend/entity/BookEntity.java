package com.library.library_backend.entity;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * Entity representing a book stored in the database.
 *
 * This entity extends {@link BaseEntity} and contains only
 * domain-specific fields related to a book.
 *
 * Database identifiers and timestamps are inherited
 * and NOT exposed through the REST API.
 */
@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    /** Public UUID used by REST API and clients */
    @Column(nullable = false, unique = true, updatable = false)
    private String bookId = UUID.randomUUID().toString();

    /** Book title */
    @Column(nullable = false)
    private String title;

    /** Book author */
    @Column(nullable = false)
    private String author;

    /** ISBN number */
    private String isbn;

    /** Year the book was published */
    private Integer publishedYear;

    /** Availability status */
    private Boolean available = true;

    // --- Getters & Setters ---

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
