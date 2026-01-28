package com.library.library_backend.service;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.entity.BookEntity;
import com.library.library_backend.exception.NotFoundException;
import com.library.library_backend.mapper.BookMapper;
import com.library.library_backend.repository.BookRepository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for the Book domain.
 *
 * <p>All business logic related to books is handled here.
 * Controllers should never access repositories directly.
 *
 * <p>This service handles CRUD operations, search, pagination,
 * and availability toggling, while mapping entities to DTOs.
 */
@Service
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // ===========================
    // READ
    // ===========================

    /**
     * Get all books in the library.
     *
     * @return list of all books as DTOs
     */
    public List<BookResponse> getAllBooks() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /**
     * Get a page of books (pagination support).
     *
     * @param pageable page request object (page number, size, sort)
     * @return page of books as DTOs
     */
    public Page<BookResponse> getBooksPage(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    /**
     * Search books by title or author (case-insensitive).
     *
     * @param query search query
     * @return list of matching books as DTOs
     */
    public List<BookResponse> searchBooks(String query) {
        return repository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /**
     * Get a single book by its unique ID.
     *
     * @param bookId book's unique identifier
     * @return book as DTO
     * @throws NotFoundException if book does not exist
     */
    public BookResponse getBook(String bookId) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return mapper.toResponse(entity);
    }

    // ===========================
    // WRITE
    // ===========================

    /**
     * Add a new book to the library.
     *
     * @param request book data
     * @return created book as DTO
     */
    @Transactional
    public BookResponse addBook(BookRequest request) {
        BookEntity entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    /**
     * Update an existing book's information.
     *
     * @param bookId  book's unique identifier
     * @param request updated book data
     * @return updated book as DTO
     * @throws NotFoundException if book does not exist
     */
    @Transactional
    public BookResponse updateBook(String bookId, BookRequest request) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        mapper.updateEntityFromRequest(request, entity);
        return mapper.toResponse(entity);
    }

    /**
     * Toggle the availability of a book (e.g., checked out / available).
     *
     * @param bookId book's unique identifier
     * @return updated book as DTO
     * @throws NotFoundException if book does not exist
     */
    @Transactional
    public BookResponse toggleAvailability(String bookId) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        entity.setAvailable(!entity.getAvailable());
        return mapper.toResponse(entity);
    }

    /**
     * Delete a book from the library.
     *
     * @param bookId book's unique identifier
     * @throws NotFoundException if book does not exist
     */
    @Transactional
    public void deleteBook(String bookId) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        repository.delete(entity);
    }
}
