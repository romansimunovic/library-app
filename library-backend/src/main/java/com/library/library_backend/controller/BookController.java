package com.library.library_backend.controller;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing books in the library.
 *
 * All endpoints communicate via DTOs (BookRequest / BookResponse) and never expose database entities.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    /* ===========================
       GET ALL / PAGINATED / SEARCH
       =========================== */

    @GetMapping
    public List<BookResponse> getAllBooks() {
        logger.info("[REQUEST] GET /api/books");
        List<BookResponse> books = service.getAllBooks();
        logger.info("[RESPONSE] 200 OK | Books count: {}", books.size());
        return books;
    }

    @GetMapping("/page")
    public Page<BookResponse> getBooksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        logger.info("[REQUEST] GET /api/books/page?page={}&size={}", page, size);
        Page<BookResponse> bookPage = service.getBooksPage(PageRequest.of(page, size));
        logger.info("[RESPONSE] 200 OK | Page size: {}", bookPage.getNumberOfElements());
        return bookPage;
    }

    @GetMapping("/search")
    public List<BookResponse> searchBooks(@RequestParam String q) {
        logger.info("[REQUEST] GET /api/books/search?q={}", q);
        List<BookResponse> result = service.searchBooks(q);
        logger.info("[RESPONSE] 200 OK | Results count: {}", result.size());
        return result;
    }

    /* ===========================
       GET BY ID
       =========================== */

    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable String bookId) {
        logger.info("[REQUEST] GET /api/books/{}", bookId);
        BookResponse book = service.getBook(bookId);
        logger.info("[RESPONSE] 200 OK | Book: {}", book);
        return book;
    }

    /* ===========================
       CREATE / UPDATE
       =========================== */

    @PostMapping
public ResponseEntity<BookResponse> addBook(
        @RequestBody @Valid BookRequest request
) {
    BookResponse createdBook = service.addBook(request);

    URI location = URI.create(
            "/api/books/" + createdBook.getBookId()
    );

    return ResponseEntity
            .created(location)
            .body(createdBook);
}


    @PutMapping("/{bookId}")
    public BookResponse updateBook(@PathVariable String bookId, @RequestBody @Valid BookRequest request) {
        logger.info("[REQUEST] PUT /api/books/{} | Body: {}", bookId, request);
        BookResponse updatedBook = service.updateBook(bookId, request);
        logger.info("[RESPONSE] 200 OK | Book: {}", updatedBook);
        return updatedBook;
    }

    @PutMapping("/{bookId}/toggle-availability")
    public BookResponse toggleAvailability(@PathVariable String bookId) {
        logger.info("[REQUEST] PUT /api/books/{}/toggle-availability", bookId);
        BookResponse response = service.toggleAvailability(bookId);
        logger.info("[RESPONSE] 200 OK | Book availability toggled: {}", response);
        return response;
    }

    /* ===========================
       DELETE
       =========================== */

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        logger.info("[REQUEST] DELETE /api/books/{}", bookId);
        service.deleteBook(bookId);
        logger.info("[RESPONSE] 204 No Content | Book deleted");
        return ResponseEntity.noContent().build();
    }
}
