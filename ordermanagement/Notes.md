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