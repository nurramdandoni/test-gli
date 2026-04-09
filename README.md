# Warehouse Management API

## Overview

This project is a simple RESTful API for managing a shop's warehouse inventory.
It supports managing items, their variants (e.g., size, color), pricing, and stock control.

The system ensures that items cannot be purchased when stock is insufficient.

---

## Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* H2 Database (in-memory)
* Swagger / OpenAPI (springdoc)

---

## How to Run the Application

### 1. Clone the repository

```bash
git clone https://github.com/nurramdandoni/test-gli.git
cd shop
```

### 2. Run the application

If using Gradle:

```bash
./gradlew bootRun ( This Project Use Gradle )
```

If using Maven:

```bash
./mvnw spring-boot:run
```

---

## API Documentation

After the application is running, open:

```
http://localhost:8080/swagger-ui/index.html
```

You can test all endpoints directly from the Swagger UI.

---

## H2 Database Console

The application uses an in-memory H2 database for simplicity.

To access the database console:

```
http://localhost:8080/h2-console
```

Use the following configuration:

```
JDBC URL: jdbc:h2:mem:warehouse_db
Username: sa
Password: (leave empty)
```

---

## Features

* Create and manage items
* Create and manage item variants
* Track stock per variant
* Prevent purchase when stock is insufficient
* Input validation
* Proper HTTP status handling (404, 400, 500)

---

## Design Decisions

### Variant holds price and stock

Price and stock are stored at the variant level instead of the item level.

Reason:

* Each variant (e.g., size, color) can have different prices and stock levels
* This reflects real-world e-commerce scenarios

---

### Use of DTO (Data Transfer Object)

Only request DTOs are used for input.

Reason:

* Prevent exposing internal entity structure
* Avoid issues like client sending ID manually
* Improve API safety and clarity

---

### Global Exception Handling

Custom exceptions (`NotFoundException`, `BadRequestException`) are used and mapped using `@RestControllerAdvice`.

Reason:

* Ensure consistent error responses
* Return appropriate HTTP status codes:

  * 404 for not found
  * 400 for bad request
  * 500 for unexpected errors

---

### Transactional Purchase Logic

The purchase operation is wrapped with `@Transactional`.

Reason:

* Ensure data consistency when updating stock
* Prevent partial updates

---

### Database Choice (H2)

H2 in-memory database is used by default.

Reason:

* No setup required
* Fast and suitable for development/testing

---

## Assumptions

* No authentication/authorization is implemented
* No transaction history is stored (only stock update)
* Each variant belongs to exactly one item
* Stock cannot go below zero
* Simple purchase logic without concurrency handling

---

## API Endpoints

All endpoints are documented and can be tested via Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

Main endpoints:

* `POST /items` → Create item

* `GET /items` → Get all items

* `GET /items/{id}` → Get item by ID

* `DELETE /items/{id}` → Delete item

* `POST /variants/item/{itemId}` → Create variant

* `GET /variants/item/{itemId}` → Get variants by item

* `POST /variants/{id}/purchase?qty=1` → Purchase variant

---

## Database Migration (Optional)

To switch from H2 to PostgreSQL or MySQL:

1. Update datasource configuration in `application.yml`

Example for PostgreSQL:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/warehouse_db
    username: your_username
    password: your_password

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

2. Run the application again

No code changes are required because the application uses JPA (database-agnostic).

---

## Author

Doni Nurramdan
