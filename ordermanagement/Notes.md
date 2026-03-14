# Order Management System Learning Plan

This project simulates a real world backend system similar to Amazon or Zomato.

The goal is to learn backend architecture step by step while building a working system.

## Development Approach

The system will be built using Domain-First Development.

Steps:

1. Define domain entities
2. Define relationships
3. Implement repositories
4. Implement service layer
5. Implement REST APIs
6. Add business logic
7. Integrate payment and inventory

## Core Domains

Customer
Address
Product
Order
OrderItem
Payment

## Key Learning Goals

- Domain Modeling
- JPA Relationships
- Transaction Management
- Order Lifecycle Management
- Payment Workflow
- Event Driven Architecture

# Module 2 — Customer Entity

## What is a Customer

Customer represents the user who places orders in the system.

Every order must belong to a customer.

Example platforms using this concept include Amazon, Zomato, and Swiggy.

## Why Customer Entity Exists

Without a customer entity the system cannot determine:

- who placed an order
- where to send notifications
- which orders belong to which user

## Entity Fields

Customer
- id
- name
- email
- phone
- createdAt

## Why ID Is Used Instead of Email

Email addresses may change over time.

Using email as the primary key can break relationships in the database.

Instead we use a generated numeric id that never changes.

## Database Table

customers

id
name
email
phone
created_at

## JPA Annotations Used

@Entity  
Marks the class as a JPA entity.

@Table  
Maps the entity to a specific database table.

@Id  
Defines the primary key.

@GeneratedValue  
Allows the database to generate IDs automatically.

# Module 3 — Address Entity

## What is Address

Address represents a delivery location for a customer.

Customers may have multiple delivery locations such as home, office, or relatives' homes.

## Why Address is Separate Entity

If address fields were stored directly inside the Customer table, the system would only allow one address per customer.

Separating Address into its own table allows one customer to store multiple addresses.

Relationship:

Customer (1) → Address (*)

## Database Design

addresses

id
customer_id
street
city
state
postal_code
country
created_at

## JPA Mapping

@ManyToOne
@JoinColumn(name = "customer_id")

Each address belongs to a specific customer.

## Java Features Used

LocalDateTime (Java 8)

Advantages:
- immutable
- thread safe
- better API compared to java.util.Date

## SOLID Principle

Single Responsibility Principle (SRP)

Customer handles user identity.

Address handles delivery location.