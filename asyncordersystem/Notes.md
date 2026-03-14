# Async Order Processing System – Engineering Notes

Author: Sushant
Goal: Master **Spring Boot, REST APIs, Java 17, Executor Framework, Docker, Design Patterns, and SOLID principles** by building a real production-style backend system.

---

# 📦 Project Goal

Build an **Async Order Processing System** where:

1. Client places an order
2. Order is processed asynchronously
3. System updates order status
4. Client can check status

Example flow:

```
Client → API → Service → Background Worker → Database
```

This mimics real-world backend systems like **Amazon order processing** or **food delivery systems**.

---

# Module 1 – Project Foundation

## 🎯 Objective

Create the **base Spring Boot project structure** using a **clean layered architecture** that supports:

* REST APIs
* Async processing
* Executor Framework
* Database integration
* Docker deployment

No business logic yet — only **proper engineering setup**.

---

# 🏗 Project Structure

```
async-order-system
│
├── src/main/java/com/sushant/asyncordersystem
│
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── config
│   ├── exception
│   └── util
│
├── Notes.md
├── README.md
└── pom.xml
```

---

# Why This Structure?

Without structure, applications become **spaghetti code**.

Bad design example:

```
Controller → Database directly
```

Problems:

* Hard to maintain
* Hard to test
* Business logic scattered
* Difficult to scale

Good design uses **layer separation**.

```
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

Benefits:

* Clean code
* Easy maintenance
* Testability
* Scalability
* Industry standard architecture

---

# Layer Responsibilities

## Controller Layer

Handles **HTTP requests from clients**.

Example requests:

```
POST /orders
GET /orders
GET /orders/{id}
DELETE /orders/{id}
```

Responsibilities:

* Receive request
* Validate input
* Call service layer
* Return response

Controller **should NOT contain business logic**.

Example skeleton:

```java
@RestController
@RequestMapping("/orders")
public class OrderController {

}
```

---

# Service Layer

Contains **business logic of the application**.

Examples:

* Create order
* Cancel order
* Process order
* Update order status

Controller delegates work to service.

Example:

```java
@Service
public class OrderService {

}
```

Why separate service layer?

Because business logic should remain **independent from HTTP layer**.

---

# Repository Layer

Handles **database access**.

We use **Spring Data JPA** which implements repository pattern automatically.

Example:

```java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
```

Spring automatically generates implementation.

Advantages:

* No SQL boilerplate
* Clean database abstraction
* Faster development

---

# Entity Layer

Represents **database tables**.

Each entity object corresponds to **one table row**.

Example structure:

```
Order
 ├─ id
 ├─ customerName
 ├─ product
 ├─ price
 ├─ status
 └─ createdAt
```

Example entity skeleton:

```java
@Entity
public class Order {

}
```

---

# DTO Layer

DTO stands for **Data Transfer Object**.

DTOs transfer data between:

```
Client ↔ Backend
```

Example DTOs:

```
OrderRequest
OrderResponse
```

Example request from client:

```json
{
  "customerName": "Sushant",
  "product": "Laptop",
  "price": 50000
}
```

Why not expose entity directly?

Reasons:

* Security
* API flexibility
* Avoid exposing internal database schema
* Support API versioning

Architecture:

```
Client → DTO → Service → Entity → Database
```

This is called the **DTO Pattern**.

---

# Config Layer

Contains application configuration.

Examples:

* Thread pool configuration
* Bean configuration
* Security configuration
* ExecutorService setup

Example:

```java
@Configuration
public class ExecutorConfig {

}
```

We will configure **Executor Framework here later**.

---

# Exception Layer

Handles **application errors globally**.

Instead of writing try/catch everywhere, Spring allows centralized exception handling.

Example:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

}
```

Benefits:

* Cleaner controllers
* Consistent API responses
* Centralized error handling

---

# Util Layer

Contains **utility helper classes**.

Examples:

```
DateUtils
Constants
Common helpers
```

Used across the application.

---

# Design Patterns Used

## 1. Layered Architecture Pattern

Application is divided into logical layers.

```
Controller → Service → Repository
```

Benefits:

* Separation of concerns
* Maintainability
* Scalability

Most enterprise systems follow this pattern.

Examples:

* Netflix
* Amazon
* Uber

---

# 2. Repository Pattern

Repository pattern separates **data access logic** from business logic.

Example:

```
Service → Repository → Database
```

Business layer does not know:

* SQL queries
* Database vendor
* ORM framework

Spring Data JPA automatically implements repository pattern.

---

# SOLID Principles Applied

## SRP – Single Responsibility Principle

Each class should have **only one responsibility**.

Example:

| Layer      | Responsibility       |
| ---------- | -------------------- |
| Controller | Handle HTTP requests |
| Service    | Business logic       |
| Repository | Database operations  |

Benefits:

* Cleaner code
* Easier maintenance
* Easier debugging

---

# DIP – Dependency Inversion Principle

## Definition

High-level modules should not depend on low-level modules.

Both should depend on **abstractions (interfaces)**.

---

## Bad Design

Service directly depends on repository implementation.

```java
public class OrderService {

    private OrderRepositoryImpl repository = new OrderRepositoryImpl();

}
```

Problems:

* Tight coupling
* Hard to replace database
* Difficult to test

---

## Good Design (Following DIP)

Service depends on repository **interface**.

```java
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

}
```

Spring automatically injects implementation.

---

# How Spring Implements This

Repository interface:

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
}
```

Spring generates implementation automatically.

Service depends on:

```
OrderRepository interface
```

Not the implementation.

---

# Real Advantage – Database Change

Suppose initially we use:

```
PostgreSQL
```

Later system changes to:

```
MySQL
MongoDB
Oracle
```

Service code **does not change**.

Only database configuration changes.

Example:

Before:

```
spring.datasource.url=jdbc:postgresql://localhost/orders
```

After:

```
spring.datasource.url=jdbc:mysql://localhost/orders
```

Service layer remains untouched.

---

# Real World Analogy

Think of **mobile phone charger**.

Phone depends on **USB interface**, not charger brand.

Any charger implementing USB works.

```
Phone → USB Interface
Charger → Implementation
```

Similarly:

```
Service → Repository Interface
Database → Implementation
```

---

# How DIP Helps Testing

Because service depends on interface, we can mock repository.

Example:

```java
@Mock
OrderRepository orderRepository;
```

Now service can be tested **without connecting to real database**.

Benefits:

* Faster tests
* Isolated testing
* Better reliability

---

# Key Takeaways

Clean architecture ensures:

* Loose coupling
* Maintainability
* Scalability
* Testability

Modern backend systems rely heavily on:

* Layered architecture
* Repository pattern
* SOLID principles

# Module 2 – Order REST API

## What We Built

Implemented first REST APIs for order management.

Endpoints:

POST /orders  
GET /orders  
GET /orders/{id}  
DELETE /orders/{id}

DTOs were introduced to separate API contract from internal entities.

## Java 17 Feature Used

Records were used for DTOs to reduce boilerplate code.

Example:

public record OrderRequest(String customerName,String product,double price){}

Records automatically generate:

- constructor
- getters
- equals
- hashCode
- toString

## Design Pattern

DTO Pattern

Used to transfer data between client and server without exposing entity.

## SOLID Principle

SRP – Controller handles HTTP requests while service handles business logic.

## Key Learning

API layer should remain independent from persistence layer.


# Module 3 – Database Integration (JPA + PostgreSQL)

## What We Built

Replaced in-memory storage with PostgreSQL database using Spring Data JPA.

Architecture:

Controller → Service → Repository → Hibernate → PostgreSQL

## ORM (Object Relational Mapping)

ORM maps Java objects to relational database tables.

Example mapping:

Order object → orders table

Fields map to columns.

Hibernate handles conversion between objects and SQL queries.

## Repository Pattern

Repository layer abstracts database operations.

Example:

OrderRepository extends JpaRepository

Spring automatically generates implementation.

## SOLID Principle

Dependency Inversion Principle

Service depends on repository interface rather than database implementation.

This ensures loose coupling and easy database replacement.

## Key Learning

Spring Data JPA significantly reduces boilerplate database code and promotes clean architecture.

# Module 3.1 – Local Database Setup using Docker

## Why Docker for Database?

Using Docker ensures consistent development environments.

Benefits:

- No need to install PostgreSQL locally
- Same environment across developers
- Easy database reset
- Easy containerized deployments

Architecture:

Spring Boot App → PostgreSQL Docker Container

## Services Used

Two containers are used:

1. PostgreSQL – database server
2. pgAdmin – database management UI

## PostgreSQL Container

Image:

postgres:16

Environment variables automatically create database credentials.

POSTGRES_DB → orders_db  
POSTGRES_USER → postgres  
POSTGRES_PASSWORD → postgres

Port mapping:

5432:5432

Host machine can access database using localhost:5432.

## Data Persistence

Docker volumes are used:

postgres_data:/var/lib/postgresql/data

This ensures database data remains even if container restarts.

## pgAdmin

pgAdmin provides web interface for PostgreSQL.

Accessible at:

http://localhost:5050

Used for:

- Viewing tables
- Running queries
- Database debugging

## Docker Networking

Both containers are connected using a bridge network.

Network name:

order-network

This allows pgAdmin to connect to PostgreSQL using service name:

postgres

instead of localhost.

## Key Learning

Using Docker for infrastructure components like databases is a common practice in modern backend development.

# Module 4 – Global Exception Handling

## What We Built

Implemented centralized exception handling using Spring Boot.

Instead of returning raw stack traces, APIs now return structured error responses.

Example error response:

{
 "status": false,
 "message": "Order not found",
 "errorCode": "ORDER_NOT_FOUND"
}

## Custom Exception

A custom exception `OrderNotFoundException` was created for domain-specific errors.

This improves clarity and allows mapping exceptions to specific HTTP responses.

## Global Exception Handler

Spring provides `@RestControllerAdvice` for centralized exception handling.

Example:

@RestControllerAdvice
public class GlobalExceptionHandler

Methods annotated with `@ExceptionHandler` intercept exceptions and return custom responses.

## Validation Error Handling

Validation failures are handled using:

MethodArgumentNotValidException

This ensures consistent validation error responses.

## Design Pattern

Template Method Pattern

Spring's exception resolution follows a predefined flow where developers plug in custom handlers.

## SOLID Principle

Single Responsibility Principle

Controllers focus on request handling while exception handling is delegated to a separate class.

## Key Learning

Centralized exception handling improves:

- API consistency
- code readability
- maintainability
