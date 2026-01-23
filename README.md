# Library App – Backend

**Mentor:** Asistent Marko Buljan  
**Kolegij:** Programiranje 3 – Filozofski fakultet Osijek, 2. godina diplomskog studija  
**Autor:** Roman Šimunović  
**Repozitorij:** [https://github.com/romansimunovic/library-app](https://github.com/romansimunovic/library-app)

---

## Opis projekta

`Library App` je REST API backend za upravljanje bibliotečnim knjigama.  
Projekt omogućava:

- Dohvat svih knjiga
- Dohvat pojedinačne knjige prema UUID identifikatoru (`bookId`)
- Kreiranje novih knjiga
- Ažuriranje postojećih knjiga
- Brisanje knjiga

Sve operacije se izvode **preko UUID `bookId`** kako bi se izbjeglo korištenje internih baze ID-a i omogućila veća sigurnost i stabilnost API-ja.

Projekt je razvijen u okviru kolegija **Programiranje 3** koristeći **Spring Boot**, **PostgreSQL** i standardne Java tehnologije.

---

## Tehnologije i korištena rješenja

- **Java 17 / Spring Boot 3** – omogućuje brzo i modularno razvijanje REST API-ja
- **Spring Data JPA** – za jednostavan rad s bazom podataka (CRUD operacije)
- **PostgreSQL** – relacijska baza podataka
- **SLF4J / Logger** – praćenje događaja i logiranje operacija u terminal
- **DTO (BookRequest / BookResponse)** – razdvajanje entiteta i REST komunikacije
- **UUID za `bookId`** – sigurniji i stabilniji identifikator od autoinkrementalnog ID-a

---

## Poteškoće i rješenja

1. **UUID vs Long ID**  
   - Na početku sam koristio Long ID iz baze, što nije bilo pogodno za REST API.  
   - Rješenje: generiranje **UUID-a** u entitetu `BookEntity` i korištenje `bookId` za sve CRUD operacije.

2. **Prikaz requestova u terminalu (VS Code)**  
   - Po defaultu Spring Boot ne prikazuje svaki HTTP request.  
   - Rješenje: dodan **logger u `BookController` i `BookService`**, te konfiguracija `logging.level.org.springframework.web=DEBUG` u `application.properties`.

3. **Exception handling za REST API**  
   - Na početku sam koristio `RuntimeException`, što nije profesionalno za REST.  
   - Preporuka: koristiti `ResponseStatusException` u kontroleru za vraćanje **HTTP 404** kada knjiga nije pronađena.

4. **Transakcije prilikom brisanja**  
   - Brisanje knjiga ponekad nije radilo bez transakcija.  
   - Rješenje: dodana anotacija `@Transactional` u metodu `deleteBook` kako bi EntityManager mogao upravljati operacijom.

---

## Instalacija i pokretanje

### 1. Kloniranje repozitorija
```bash
git clone https://github.com/romansimunovic/library-app.git
cd library-app
````

### 2. Konfiguracija baze podataka

* Napraviti lokalnu PostgreSQL bazu ili koristiti postojeću.
* Napraviti `application.properties` koristeći primjer:

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

> **Napomena:** Zamijeniti `<HOST>`, `<PORT>`, `<DATABASE_NAME>`, `<DB_USERNAME>` i `<DB_PASSWORD>` stvarnim podacima.

### 3. Pokretanje backend servera

```bash
./mvnw spring-boot:run
```

ili ako koristiš Maven:

```bash
mvn spring-boot:run
```

* Server će se pokrenuti na `http://localhost:8080`.
* Svi HTTP requestovi će biti vidljivi u terminalu zahvaljujući loggeru.

### 4. Testiranje API-ja

* Preporučeno: [Postman](https://www.postman.com/) ili `curl`.
* Primjeri endpointova:

  * `GET /api/books` – dohvat svih knjiga
  * `GET /api/books/{bookId}` – dohvat jedne knjige
  * `POST /api/books` – kreiranje nove knjige
  * `PUT /api/books/{bookId}` – ažuriranje knjige
  * `DELETE /api/books/{bookId}` – brisanje knjige

---

## Struktura projekta

```
library-backend/
├── src/main/java/com/library/library_backend
│   ├── controller/BookController.java
│   ├── service/BookService.java
│   ├── repository/BookRepository.java
│   ├── entity/BookEntity.java
│   └── dto/BookRequest.java, BookResponse.java
├── src/main/resources/application.properties
└── pom.xml
```

---

## Zaključak

Projekt `Library App` omogućuje jednostavno upravljanje knjigama u biblioteci preko REST API-ja.
Kroz razvoj sam naučio važnost:

* Korištenja UUID identifikatora za stabilnost i sigurnost API-ja
* Logiranja i praćenja requestova u terminalu
* Ispravnog upravljanja transakcijama u Spring Bootu
* Dizajna REST API-ja s DTO-ovima i profesionalnim error handlingom

---
