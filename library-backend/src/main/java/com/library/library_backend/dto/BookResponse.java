package com.library.library_backend.dto;

/**
 * DTO za REST API - izlazni podaci za korisnika.
 */
public class BookResponse {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private boolean available;

    // Getters i Setters
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
}
