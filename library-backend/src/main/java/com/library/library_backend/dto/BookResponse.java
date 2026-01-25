package com.library.library_backend.dto;

/**
 * Response DTO used for exposing book data via REST API.
 *
 * Contains all BookEntity fields except BaseEntity attributes.
 */
public class BookResponse {

    /** Public UUID identifier */
    private String bookId;

    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private Boolean available;

    // Getters & Setters

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
