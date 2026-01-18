package com.library.library_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @NotBlank(message = "Naslov knjige ne smije biti prazan")
    private String title;

    @NotBlank(message = "Autor ne smije biti prazan")
    private String author;

    @Size(min = 10, max = 13, message = "ISBN mora imati 10–13 znakova")
    private String isbn; // nullable, validacija se primjenjuje samo ako nije null

    @Min(value = 0, message = "Godina izdanja mora biti pozitivna")
    private Integer publishedYear; // nullable

    private Boolean available = true; // default true

    // getters & setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
