# Library App Backend 

**Mentor:** Asistent Marko Buljan
**Kolegij:** Programiranje 3 – Filozofski fakultet Osijek, 2. godina diplomskog studija
**Autor:** Roman Šimunović
**Repozitorij:** [GitHub](https://github.com/romansimunovic/library-app)

REST API backend za upravljanje knjigama u biblioteci. Sve CRUD operacije koriste **UUID `bookId`** za stabilnost i sigurnost, a projekt je razvijen u okviru kolegija **Programiranje 3** koristeći **Spring Boot** i **PostgreSQL**.

---

## 🔹 Ključne funkcionalnosti

* Dohvat svih knjiga (`GET /api/books`)
* Dohvat pojedinačne knjige prema UUID (`GET /api/books/{bookId}`)
* Kreiranje nove knjige (`POST /api/books`)
* Ažuriranje postojeće knjige (`PUT /api/books/{bookId}`)
* Brisanje knjige (`DELETE /api/books/{bookId}`)

Sve operacije koriste **UUID** kako bi se izbjeglo korištenje internih baze ID-a i omogućila veća sigurnost.

---

## ⚡ Tehnologije i rješenja

* **Java 17 / Spring Boot 3** – modularan i brz REST API
* **Spring Data JPA** – jednostavno upravljanje CRUD operacijama
* **PostgreSQL** – relacijska baza podataka
* **DTO** (`BookRequest` / `BookResponse`) – odvajanje entiteta i REST komunikacije
* **SLF4J / Logger** – praćenje i logiranje svih operacija
* **UUID za `bookId`** – stabilniji i sigurniji od autoinkrementalnog ID-a

---

## 🛠️ Poteškoće i rješenja

1. **UUID vs Long ID**

   * Problem: REST API s Long ID-om nije bio optimalan.
   * Rješenje: `BookEntity` sada generira **UUID**, sve CRUD operacije koriste `bookId`.

2. **Prikaz requestova u terminalu**

   * Problem: Spring Boot po defaultu ne prikazuje sve HTTP requestove.
   * Rješenje: Logger u `BookController` i `BookService`, plus `logging.level.org.springframework.web=DEBUG`.

3. **Exception handling**

   * Problem: Korištenje `RuntimeException` nije profesionalno za REST.
   * Rješenje: `ResponseStatusException` za vraćanje HTTP 404 kada knjiga nije pronađena.

4. **Transakcije pri brisanju knjiga**

   * Problem: Brisanje nije uvijek radilo bez transakcija.
   * Rješenje: Anotacija `@Transactional` u `deleteBook` metodi.

---

## 🚀 Instalacija i pokretanje

### 1. Kloniranje repozitorija

```bash
git clone https://github.com/romansimunovic/library-app.git
cd library-app
```

### 2. Konfiguracija baze

U `application.properties` postaviti podatke o PostgreSQL bazi:

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
.\mvnw spring-boot:run
```

* Server se pokreće na `http://localhost:8080`.
* Svi HTTP requestovi su vidljivi u terminalu zahvaljujući loggeru.

### 4. Testiranje API-ja

* Alati: [Postman](https://www.postman.com/) ili `curl`.
* Endpoint primjeri:

| Metoda | Endpoint            | Opis                  |
| ------ | ------------------- | --------------------- |
| GET    | /api/books          | Dohvat svih knjiga    |
| GET    | /api/books/{bookId} | Dohvat jedne knjige   |
| POST   | /api/books          | Kreiranje nove knjige |
| PUT    | /api/books/{bookId} | Ažuriranje knjige     |
| DELETE | /api/books/{bookId} | Brisanje knjige       |

---

## 🗂️ Struktura projekta

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

Projekt `Library App` backend omogućuje:

* Stabilno i sigurno upravljanje knjigama preko REST API-ja
* Profesionalan error handling i logiranje requestova
* Učenje važnosti UUID identifikatora, transakcija i DTO dizajna
