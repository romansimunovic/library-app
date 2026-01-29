# Library App Backend

* Course: Programming 3 – Faculty of Humanities and Social Sciences, University of Osijek
* Study Level: 2nd Year Master’s Programme
* Mentor: Marko Buljan
* Author: Roman Šimunović

## Overview

This project is a RESTful backend application for managing books in a library system. It was developed as part of the *Programming 3* course and follows modern backend development practices using **Spring Boot** and **PostgreSQL**.

The application exposes a clean REST API that supports full CRUD functionality. All resources are identified using **UUID-based identifiers (`bookId`)**, ensuring API stability, security, and independence from database-generated primary keys.

The project emphasizes proper architectural separation, transactional safety, structured logging, and standardized error handling.

---

## Core Functionality

The backend provides the following REST endpoints:

| HTTP Method | Endpoint              | Description             |
| ----------- | --------------------- | ----------------------- |
| GET         | `/api/books`          | Retrieve all books      |
| GET         | `/api/books/{bookId}` | Retrieve a book by UUID |
| POST        | `/api/books`          | Create a new book       |
| PUT         | `/api/books/{bookId}` | Update an existing book |
| DELETE      | `/api/books/{bookId}` | Delete a book by UUID   |

All operations use a UUID (`bookId`) as the public identifier rather than database auto-increment IDs.

---

## Technology Stack

* **Java 17** – modern LTS Java version
* **Spring Boot 3** – REST API framework
* **Spring Data JPA** – data persistence abstraction
* **PostgreSQL** – relational database
* **Hibernate** – ORM implementation
* **Maven** – dependency and build management
* **SLF4J / Spring Logging** – request and operation logging

---

## Architectural Design

### UUID-Based Resource Identification

Instead of exposing database primary keys, the API uses UUIDs for all book resources. This approach:

* Prevents enumeration attacks
* Decouples API design from database internals
* Improves long-term API stability

### DTO-Based Communication

The application uses dedicated Data Transfer Objects:

* `BookRequest` – input validation and request handling
* `BookResponse` – controlled output representation

This ensures that persistence entities are never directly exposed through the API.

### Layered Structure

* **Controller layer** – handles HTTP requests and responses
* **Service layer** – contains business logic and transactions
* **Repository layer** – manages database access
* **Entity layer** – represents database models

---

## Error Handling and Logging

### Centralized Exception Handling

A global exception handler implemented using `@RestControllerAdvice` ensures:

* Consistent JSON error responses
* Appropriate HTTP status codes
* Improved debuggability and API professionalism

### Logging Strategy

* All incoming HTTP requests are logged
* CRUD operations are logged at the service level
* SQL queries and parameter bindings can be enabled for debugging

This provides full visibility into application behavior during development and testing.

---

## Transaction Management

Transactional integrity is enforced using `@Transactional` where required. This guarantees:

* Consistent update and delete operations
* Proper rollback behavior on failures
* Database state integrity

---

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/romansimunovic/library-app.git
cd library-app
```

### 2. Configure the Database

Update the PostgreSQL configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.address=0.0.0.0
server.port=8080

logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

Replace the placeholders with valid database credentials.

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

The backend will start at:

```
http://localhost:8080
```

---

## API Testing

The API can be tested using tools such as **Postman** or `curl`. All endpoints follow standard REST conventions and return JSON responses.

---

## Conclusion

This project demonstrates:

* Proper REST API design using Spring Boot
* Secure UUID-based resource identification
* Clean architectural separation using DTOs and service layers
* Centralized error handling and structured logging
* Transaction-safe database operations

The application serves as a solid foundation for further extension, such as authentication, pagination, filtering, or frontend integration.

---
