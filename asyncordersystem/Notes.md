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
