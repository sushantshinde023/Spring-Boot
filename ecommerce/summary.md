# E-Commerce Backend System - Summary (Spring Boot)

## 1. Project Setup

-   Spring Boot 3, Java 17+
-   Dependencies: Web, JPA, PostgreSQL, Lombok, Validation, Security

------------------------------------------------------------------------

## 2. Clean Architecture

-   Controller → Handles HTTP requests
-   Service → Business logic
-   Repository → DB interaction

------------------------------------------------------------------------

## 3. Entity Layer

-   Product Entity
-   User Entity (id, name, email, password, role)
-   Role Enum (USER, ADMIN)

------------------------------------------------------------------------

## 4. Repository Layer

-   JpaRepository used
-   Custom method: findByEmail()

------------------------------------------------------------------------

## 5. Service Layer

-   Business logic implementation
-   Password encoding using BCrypt

------------------------------------------------------------------------

## 6. Controller Layer

-   REST APIs for CRUD
-   Register & Login APIs

------------------------------------------------------------------------

## 7. DTO Layer

-   Used record for DTOs
-   Prevents exposing entity directly

------------------------------------------------------------------------

## 8. Validation

-   @NotNull, @Email, etc.
-   Handled using @Valid

------------------------------------------------------------------------

## 9. Global Exception Handling

-   @RestControllerAdvice
-   Custom ErrorResponse
-   Handles:
    -   ResourceNotFoundException
    -   Validation errors

------------------------------------------------------------------------

## 10. Security (Basic + JWT)

### Basic

-   Disabled CSRF (stateless)
-   Secured endpoints

### JWT

-   JwtUtil (generate, validate token)
-   Login API returns JWT
-   Password matching using BCrypt

------------------------------------------------------------------------

## 11. JWT Filter

-   Extends OncePerRequestFilter
-   Extracts token from header
-   Validates token
-   Sets authentication in SecurityContext

------------------------------------------------------------------------

## 12. Security Configuration

-   SecurityFilterChain
-   Permit /auth/\*\*
-   Protect other APIs
-   Stateless session
-   Add JWT filter before UsernamePasswordAuthenticationFilter

------------------------------------------------------------------------

## 13. Final Outcome

-   Production-ready backend
-   Stateless authentication
-   Clean architecture
-   Secure APIs
