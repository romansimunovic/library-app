# Library App Backend

* Mentor: Assistant Marko Buljan
* Course: Programming 3 – Faculty of Humanities and Social Sciences, University of Osijek, 2nd year Master’s
* Author: Roman Šimunović

This is a **REST API backend** for managing books in a library. All CRUD operations use **UUID `bookId`** for stability and security. This project was developed as part of the **Programming 3** course using **Spring Boot** and **PostgreSQL**.

---

## 🔹 Key Features

* Retrieve all books (`GET /api/books`)
* Retrieve a single book by UUID (`GET /api/books/{bookId}`)
* Create a new book (`POST /api/books`)
* Update an existing book (`PUT /api/books/{bookId}`)
* Delete a book (`DELETE /api/books/{bookId}`)

All operations use **UUID** identifiers instead of database auto-increment IDs to enhance security and API stability.

---

## ⚡ Technologies & Solutions

* **Java 17 / Spring Boot 3** – modern, modular REST API
* **Spring Data JPA** – simplifies CRUD operations
* **PostgreSQL** – relational database for persistence
* **DTOs (`BookRequest` / `BookResponse`)** – separates entity models from API communication
* **SLF4J / Logger** – logs all operations for monitoring and debugging
* **UUID `bookId`** – more secure and stable than auto-increment IDs

---

## 🛠️ Challenges & Solutions

1. **UUID vs Long ID**
   *Problem:* Using Long IDs in the API was insecure and unstable.
   *Solution:* All `BookEntity` objects now use **UUIDs**, and all CRUD operations rely on `bookId`.

2. **Request Logging**
   *Problem:* By default, Spring Boot does not log all HTTP requests.
   *Solution:* Implemented logging in `BookController` and `BookService`, with `logging.level.org.springframework.web=DEBUG`.

3. **Exception Handling**
   *Problem:* Throwing generic `RuntimeException` is not professional for REST APIs.
   *Solution:* Implemented `@RestControllerAdvice` to return proper JSON error responses with HTTP status codes.

4. **Transactional Operations**
   *Problem:* Deleting or updating entities sometimes failed without transactions.
   *Solution:* Added `@Transactional` annotations where necessary to ensure consistent database operations.

---

## 🚀 Installation & Running

### 1. Clone the Repository

```bash
git clone https://github.com/romansimunovic/library-app.git
cd library-app
```

### 2. Configure the Database

Set PostgreSQL connection in `application.properties`:

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

> Replace `<HOST>`, `<PORT>`, `<DATABASE_NAME>`, `<DB_USERNAME>`, and `<DB_PASSWORD>` with your database credentials.

### 3. Run the Backend Server

```bash
./mvnw spring-boot:run
```

* Server runs at `http://localhost:8080`.
* All HTTP requests are logged in the terminal.

### 4. Test the API

Use [Postman](https://www.postman.com/) or `curl`. Example endpoints:

| Method | Endpoint            | Description       |
| ------ | ------------------- | ----------------- |
| GET    | /api/books          | Get all books     |
| GET    | /api/books/{bookId} | Get a single book |
| POST   | /api/books          | Create a new book |
| PUT    | /api/books/{bookId} | Update a book     |
| DELETE | /api/books/{bookId} | Delete a book     |

---

## 🗂️ Project Structure

```
library-backend/
├── src/main/java/com/library/library_backend
│   ├── controller/BookController.java
│   ├── service/BookService.java
│   ├── repository/BookRepository.java
│   ├── entity/BookEntity.java
│   ├── dto/BookRequest.java
│   └── dto/BookResponse.java
├── src/main/resources/application.properties
└── pom.xml
```

---

## ✅ Conclusion

The `Library App` backend provides:

* Secure and stable book management via REST API
* Professional logging and centralized error handling
* UUID identifiers, transactional operations, and DTO-based design
