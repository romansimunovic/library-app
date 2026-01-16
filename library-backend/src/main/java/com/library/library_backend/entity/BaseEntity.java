package com.library.library_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

/**
 * Apstraktna baza za sve entitete u aplikaciji.
 * <p>
 * Ova klasa sadrži zajednička polja koja se koriste u svim entitetima:
 * - Jedinstveni primarni ključ {@code id}
 * - Vrijeme kreiranja {@code createdAt}
 * - Vrijeme posljednje izmjene {@code modifiedAt}
 * <p>
 * Klase koje nasljeđuju {@code BaseEntity} automatski nasljeđuju ove atribute i
 * funkcionalnost automatskog ažuriranja vremena izmjene.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    /** Primarni ključ entiteta, automatski generiran */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    /** Vrijeme kreiranja entiteta, postavljeno prilikom inicijalizacije i ne može se mijenjati */
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /** Vrijeme posljednje izmjene entiteta, automatski se ažurira prilikom svakog update-a */
    @Column(nullable = false)
    private Instant modifiedAt = Instant.now();

    /**
     * Metoda koja se poziva prije svakog update-a entiteta.
     * Automatski postavlja {@code modifiedAt} na trenutno vrijeme.
     */
    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = Instant.now();
    }
}
