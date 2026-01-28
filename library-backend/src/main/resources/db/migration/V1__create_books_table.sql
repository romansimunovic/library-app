CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    book_id VARCHAR(36) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(13),
    published_year INTEGER,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL
);
