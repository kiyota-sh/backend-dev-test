# Franchise API

Backend made with Spring Boot, using Clean Architecture + Hexagonal Architecture.

## Technologies

- Java 21
- Spring Boot
- JPA / Hibernate
- MySQL
- Docker
- Swagger (OpenAPI)

---

## Architecture

- Domain (core)
- Application (use cases)
- Infrastructure (controllers, adapters, persistence)

---

## Run the project

### Option #1 Local

```bash
mvn spring-boot:run
```

### Option #2 Docker
```bash
docker-compose up --build
```

## Endpoints

### Franchise
- POST `/franchises`
- PUT `/franchises/{id}/name`

### Branches
- POST `/branches`
- PUT `/branches/{id}/name`

### Products
- GET `/products/top-stock/{franchiseId}`
- POST `/products`
- PUT `/products/{id}/name`
- PUT `/products/{id}/stock`
- DELETE `/products/{id}`

## Swagger

`http://localhost:8080/swagger-ui.html`

## Tests
```bash
mvn test
```