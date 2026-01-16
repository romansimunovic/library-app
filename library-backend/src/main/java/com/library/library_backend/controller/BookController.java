package com.library.library_backend.controller;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API za knjige.
 * 
 * GET /api/books -> sve knjige
 * GET /api/books/{bookId} -> pojedinačna knjiga
 * POST /api/books -> kreiranje knjige
 * PUT /api/books/{bookId} -> update knjige
 * DELETE /api/books/{bookId} -> brisanje knjige
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable String bookId) {
        return service.getBook(bookId);
    }

    @PostMapping
    public BookResponse addBook(@RequestBody BookRequest request) {
        return service.addBook(request);
    }

    @PutMapping("/{bookId}")
    public BookResponse updateBook(@PathVariable String bookId, @RequestBody BookRequest request) {
        return service.updateBook(bookId, request);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        boolean deleted = service.deleteBook(bookId);
        if (deleted) {
            return ResponseEntity.ok("Knjiga obrisana.");
        } else {
            return ResponseEntity.status(404).body("Knjiga nije pronađena.");
        }
    }
}
