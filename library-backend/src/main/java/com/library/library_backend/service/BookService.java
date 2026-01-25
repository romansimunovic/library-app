package com.library.library_backend.service;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.entity.BookEntity;
import com.library.library_backend.exception.NotFoundException;
import com.library.library_backend.repository.BookRepository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for Book domain.
 *
 * All business logic is handled here.
 * Communication with controllers is done exclusively via DTOs.
 */
@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /* ===========================
       READ
       =========================== */

    public List<BookResponse> getAllBooks() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Page<BookResponse> getBooksPage(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(this::mapToResponse);
    }

    public BookResponse getBook(String bookId) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return mapToResponse(entity);
    }

    public List<BookResponse> searchBooks(String query) {
        return repository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* ===========================
       CREATE
       =========================== */

    public BookResponse addBook(BookRequest request) {
        BookEntity entity = new BookEntity();
        applyRequest(entity, request);
        repository.save(entity);
        return mapToResponse(entity);
    }

    /* ===========================
       UPDATE
       =========================== */

    public BookResponse updateBook(String bookId, BookRequest request) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        applyRequest(entity, request);
        repository.save(entity);

        return mapToResponse(entity);
    }

    @Transactional
    public BookResponse toggleAvailability(String bookId) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        entity.setAvailable(!entity.getAvailable());
        return mapToResponse(entity);
    }

    /* ===========================
       DELETE
       =========================== */

    @Transactional
    public void deleteBook(String bookId) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        repository.delete(entity);
    }

    /* ===========================
       MAPPING
       =========================== */

    private void applyRequest(BookEntity entity, BookRequest request) {
        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        entity.setIsbn(request.getIsbn());
        entity.setPublishedYear(request.getPublishedYear());
        entity.setAvailable(
                request.getAvailable() != null ? request.getAvailable() : true
        );
    }

    private BookResponse mapToResponse(BookEntity entity) {
        BookResponse response = new BookResponse();
        response.setBookId(entity.getBookId());
        response.setTitle(entity.getTitle());
        response.setAuthor(entity.getAuthor());
        response.setIsbn(entity.getIsbn());
        response.setPublishedYear(entity.getPublishedYear());
        response.setAvailable(entity.getAvailable());
        return response;
    }
}
