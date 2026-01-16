package com.library.library_backend.service;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.entity.BookEntity;
import com.library.library_backend.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servis za upravljanje knjigama u biblioteci.
 * <p>
 * Ovaj servis implementira CRUD operacije nad entitetom {@link BookEntity} koristeći UUID {@code bookId}
 * kao jedinstveni identifikator za sve vanjske operacije (REST API).  
 * <p>
 * Glavne funkcionalnosti:
 * <ul>
 *     <li>Dohvat svih knjiga</li>
 *     <li>Dohvat jedne knjige prema UUID</li>
 *     <li>Kreiranje nove knjige</li>
 *     <li>Ažuriranje postojeće knjige prema UUID</li>
 *     <li>Brisanje knjige prema UUID</li>
 * </ul>
 */
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repository;

    /**
     * Konstruktor servisa.
     *
     * @param repository repozitorij za pristup bazi podataka
     */
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * Briše knjigu iz baze prema njezinom UUID {@code bookId}.
     * <p>
     * Ako knjiga postoji, briše je i vraća {@code true}.  
     * Ako ne postoji, vraća {@code false}.
     *
     * @param bookId UUID identifikator knjige
     * @return {@code true} ako je knjiga obrisana, {@code false} ako knjiga nije pronađena
     */
    @Transactional
    public boolean deleteBook(String bookId) {
        return repository.findByBookId(bookId)
                .map(book -> {
                    repository.delete(book); 
                    logger.info("Obrisana knjiga: {}", bookId);
                    return true;
                })
                .orElseGet(() -> {
                    logger.warn("Knjiga {} nije pronađena za brisanje", bookId);
                    return false;
                });
    }

    /**
     * Dohvaća sve knjige iz baze.
     *
     * @return lista {@link BookResponse} objekata koji predstavljaju sve knjige
     */
    public List<BookResponse> getAllBooks() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Dohvaća jednu knjigu prema UUID {@code bookId}.
     * <p>
     * Ako knjiga ne postoji, baca {@link RuntimeException}.  
     * (Preporučeno je koristiti {@link org.springframework.web.server.ResponseStatusException} u kontroleru za REST API)
     *
     * @param bookId UUID identifikator knjige
     * @return {@link BookResponse} objekt s detaljima knjige
     */
    public BookResponse getBook(String bookId) {
        return repository.findByBookId(bookId)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronađena"));
    }

    /**
     * Kreira novu knjigu u bazi.
     * <p>
     * UUID {@code bookId} se generira automatski u {@link BookEntity}.
     *
     * @param request objekt {@link BookRequest} s podacima za novu knjigu
     * @return {@link BookResponse} objekt koji predstavlja kreiranu knjigu
     */
    public BookResponse addBook(BookRequest request) {
        BookEntity entity = new BookEntity();
        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        entity.setIsbn(request.getIsbn());
        entity.setPublishedYear(request.getPublishedYear());
        entity.setAvailable(request.getAvailable());
        repository.save(entity);
        logger.info("Dodana nova knjiga: {}", entity.getBookId());
        return toResponse(entity);
    }

    /**
     * Ažurira postojeću knjigu prema UUID {@code bookId}.
     * <p>
     * Ako knjiga ne postoji, baca {@link RuntimeException}.  
     * <p>
     * Polja koja se ažuriraju: naslov, autor, ISBN, godina izdavanja i dostupnost.
     *
     * @param bookId UUID identifikator knjige
     * @param request objekt {@link BookRequest} s novim podacima
     * @return {@link BookResponse} objekt s ažuriranim podacima knjige
     */
    public BookResponse updateBook(String bookId, BookRequest request) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronađena"));

        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        entity.setIsbn(request.getIsbn());
        entity.setPublishedYear(request.getPublishedYear());
        entity.setAvailable(request.getAvailable());

        repository.save(entity);
        logger.info("Ažurirana knjiga: {}", bookId);
        return toResponse(entity);
    }

    /**
     * Pomoćna metoda koja konvertira {@link BookEntity} u {@link BookResponse} DTO.
     *
     * @param entity entitet knjige iz baze
     * @return {@link BookResponse} objekt
     */
    private BookResponse toResponse(BookEntity entity) {
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
