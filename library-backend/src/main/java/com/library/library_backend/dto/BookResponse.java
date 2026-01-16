package com.library.library_backend.dto;

/**
 * DTO (Data Transfer Object) za REST API koji predstavlja izlazne podatke
 * o knjizi korisniku.
 * <p>
 * Koristi se prilikom GET zahtjeva ili nakon kreiranja / ažuriranja knjige
 * da bi se korisniku vratio kompletan objekt s jedinstvenim identifikatorom.
 */
public class BookResponse {

    /** Jedinstveni identifikator knjige (UUID) */
    private String bookId;

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
