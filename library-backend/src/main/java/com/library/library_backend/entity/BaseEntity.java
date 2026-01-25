package com.library.library_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Base entity that provides common database fields for all entities.
 *
 * Contains:
 * - Auto-generated primary key (database identifier)
 * - Creation timestamp
 * - Last modification timestamp
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    /** Database primary key (not exposed via API) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Timestamp when the entity was created */
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /** Timestamp of the last entity update */
    @Column(nullable = false)
    private Instant modifiedAt = Instant.now();

    /** Automatically updates modification timestamp before update */
    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = Instant.now();
    }
}
