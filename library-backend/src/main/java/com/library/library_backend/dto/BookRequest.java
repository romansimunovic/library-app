package com.library.library_backend.dto;

/**
 * DTO (Data Transfer Object) za REST API koji predstavlja dolazni zahtjev
 * za kreiranje ili ažuriranje knjige.
 * <p>
 * Polja ovog objekta se očekuju od klijenta (npr. frontend aplikacije)
 * pri POST ili PUT zahtjevima.
 */
public class BookRequest {

    /** Naslov knjige */
    private String title;

    /** Autor knjige */
    private String author;

    /** ISBN knjige */
    private String isbn;

    /** Godina izdavanja knjige */
    private int publishedYear;

    /** Dostupnost knjige u biblioteci */
    private boolean available;

    // --- Getteri i setteri ---

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
