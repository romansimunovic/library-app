package com.library.library_backend.controller;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;

import java.util.List;

/**
 * REST API kontroler za upravljanje knjigama u biblioteci.
 * <p>
 * Svaka knjiga u sustavu ima jedinstveni UUID identifikator {@code bookId}, koji se koristi
 * za dohvat, ažuriranje i brisanje knjiga putem REST API-ja.
 * <p>
 * CRUD operacije (Create, Read, Update, Delete) su podržane nad entitetom knjige.
 * <p>
 * API Endpoints:
 * <ul>
 *     <li>GET /api/books - Dohvaća sve knjige</li>
 *     <li>GET /api/books/{bookId} - Dohvaća jednu knjigu prema UUID identifikatoru</li>
 *     <li>POST /api/books - Kreira novu knjigu</li>
 *     <li>PUT /api/books/{bookId} - Ažurira postojeću knjigu prema UUID identifikatoru</li>
 *     <li>DELETE /api/books/{bookId} - Briše knjigu prema UUID identifikatoru</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    /**
     * Konstruktor kontrolera.
     *
     * @param service servis koji upravlja logikom za knjige
     */
    public BookController(BookService service) {
        this.service = service;
    }

    /**
     * Dohvaća sve knjige iz baze.
     *
     * @return lista svih knjiga (BookResponse)
     * HTTP Status: 200 OK
     */
    @GetMapping
    public List<BookResponse> getAllBooks() {
        System.out.println("[REQUEST] GET /api/books");
        List<BookResponse> books = service.getAllBooks();
        System.out.println("[RESPONSE] 200 OK | Books count: " + books.size());
        return books;
    }

    @GetMapping("/page")
public Page<BookResponse> getBooksPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    return service.getBooksPage(PageRequest.of(page, size));
}

@GetMapping("/search")
public List<BookResponse> searchBooks(@RequestParam String q) {
    return service.searchBooks(q);
}

@PutMapping("/{bookId}/toggle-availability")
public BookResponse toggleAvailability(@PathVariable String bookId) {
    return service.toggleAvailability(bookId);
}



    /**
     * Dohvaća jednu knjigu prema njenom UUID identifikatoru {@code bookId}.
     *
     * @param bookId UUID identifikator knjige
     * @return BookResponse objekt s detaljima knjige
     * HTTP Status:
     *  - 200 OK ako je knjiga pronađena
     *  - 404 Not Found ako knjiga s tim UUID-em ne postoji
     */
    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable String bookId) {
        System.out.println("[REQUEST] GET /api/books/" + bookId);
        BookResponse book = service.getBook(bookId);
        System.out.println("[RESPONSE] 200 OK | Book: " + book);
        return book;
    }

    /**
     * Kreira novu knjigu.
     *
     * @param request objekt s podacima za novu knjigu (BookRequest)
     * @return BookResponse objekt s podacima kreirane knjige
     * HTTP Status: 201 Created
     */
    @PostMapping
public BookResponse addBook(@RequestBody @Valid BookRequest request) {
    System.out.println("[REQUEST] POST /api/books | Body: " + request);
    BookResponse createdBook = service.addBook(request);
    System.out.println("[RESPONSE] 201 Created | Book: " + createdBook);
    return createdBook;
}

    /**
     * Ažurira postojeću knjigu prema UUID identifikatoru {@code bookId}.
     *
     * @param bookId UUID identifikator knjige koju želimo ažurirati
     * @param request objekt s podacima za ažuriranje (BookRequest)
     * @return BookResponse objekt s podacima ažurirane knjige
     * HTTP Status:
     *  - 200 OK ako je knjiga uspješno ažurirana
     *  - 404 Not Found ako knjiga s tim UUID-em ne postoji
     */
    @PutMapping("/{bookId}")
public BookResponse updateBook(@PathVariable String bookId, @RequestBody @Valid BookRequest request) {
    System.out.println("[REQUEST] PUT /api/books/" + bookId + " | Body: " + request);
    BookResponse updatedBook = service.updateBook(bookId, request);
    System.out.println("[RESPONSE] 200 OK | Book: " + updatedBook);
    return updatedBook;
}

    /**
     * Briše knjigu prema UUID identifikatoru {@code bookId}.
     *
     * @param bookId UUID identifikator knjige koju želimo obrisati
     * @return ResponseEntity s porukom o rezultatu operacije
     * HTTP Status:
     *  - 200 OK ako je knjiga uspješno obrisana
     *  - 404 Not Found ako knjiga s tim UUID-em ne postoji
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        System.out.println("[REQUEST] DELETE /api/books/" + bookId);
        boolean deleted = service.deleteBook(bookId);
        if (deleted) {
            System.out.println("[RESPONSE] 200 OK - Knjiga obrisana");
            return ResponseEntity.ok("Knjiga obrisana.");
        } else {
            System.out.println("[RESPONSE] 404 Not Found - Knjiga nije pronađena");
            return ResponseEntity.status(404).body("Knjiga nije pronađena.");
        }
    }
}
