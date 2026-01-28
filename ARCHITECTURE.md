# Library Management System – Backend Architecture

## Overview
This backend application provides a RESTful API for managing books in a library.
It follows a layered architecture and enforces strict separation between:
- Persistence layer (JPA entities)
- Service layer (business logic)
- API layer (DTO-based communication)

## Architecture Layers

### Controller Layer
- Exposes REST endpoints
- Accepts and returns DTO objects only
- Does not contain business logic

### Service Layer
- Contains all business rules
- Handles transactions
- Maps between entities and DTOs

### Persistence Layer
- Uses Spring Data JPA
- Entities are not exposed outside the service layer
- UUID-based identifiers are used for public API access

## Error Handling
All exceptions are handled centrally using `@RestControllerAdvice`.
Clients receive consistent JSON error responses with:
- HTTP status
- Error description
- Request path
- Timestamp

## Audit Fields
All entities inherit from `BaseEntity`, which provides:
- Auto-generated database ID
- Creation timestamp
- Last modification timestamp

These fields are stored in the database but never exposed via the API.

## REST Compliance
- POST operations return `201 Created`
- Location header points to the newly created resource
- Proper HTTP status codes are used throughout the API
