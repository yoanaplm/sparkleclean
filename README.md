# Sparkle Clean

A booking platform for a cleaning company. Clients can register, pick cleaning services and book them for a chosen date and address. Administrators manage the services.

## Tech stack

- Java 21
- Spring Boot, Spring MVC, Spring Data JPA, Bean Validation
- Thymeleaf
- MySQL
- BCrypt password hashing (spring-security-crypto)
- Maven

## Running the application

1. Start a local MySQL server on port 3306.
2. Set the database user and password in `src/main/resources/application.properties`.
3. Run `SparklecleanApplication` and open http://localhost:8080.

The database is created automatically. An administrator account is created on first start (see `application.properties`).

## Features

- Registration and session-based login and logout
- Guests can only see the home, login and register pages
- Role-based access: clients and administrators
- Browse cleaning services and view their details
- Server-side validation with error messages on every form
- Business rules enforced with custom exceptions

## Functionalities

- Administrator: create, edit and delete cleaning services (a service with bookings or reviews cannot be deleted)
- Client: book a cleaning service for a future date between 09:00 and 17:00
- Client: leave one review per service

## Integrations

None.