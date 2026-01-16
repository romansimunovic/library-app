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

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * Briše knjigu po bookId.
     * @param bookId ID knjige
     * @return true ako je knjiga obrisana, false ako nije pronađena
     */
    @Transactional // Obavezno public i @Transactional
    public boolean deleteBook(String bookId) {
        return repository.findByBookId(bookId)
                .map(book -> {
                    repository.delete(book); // ovo koristi EntityManager iz transakcije
                    logger.info("Obrisana knjiga: {}", bookId);
                    return true;
                })
                .orElseGet(() -> {
                    logger.warn("Knjiga {} nije pronađena za brisanje", bookId);
                    return false;
                });
    }

    // Ostale metode: getAllBooks, getBook, addBook, updateBook
    public List<BookResponse> getAllBooks() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBook(String bookId) {
        return repository.findByBookId(bookId)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronađena"));
    }

    public BookResponse addBook(BookRequest request) {
        BookEntity entity = new BookEntity();
        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        entity.setIsbn(request.getIsbn());
        entity.setPublishedYear(request.getPublishedYear());
        entity.setAvailable(request.getAvailable());
        repository.save(entity);
        return toResponse(entity);
    }

    public BookResponse updateBook(String bookId, BookRequest request) {
        BookEntity entity = repository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronađena"));

        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        entity.setIsbn(request.getIsbn());
        entity.setPublishedYear(request.getPublishedYear());
        entity.setAvailable(request.getAvailable());

        repository.save(entity);
        return toResponse(entity);
    }

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
