# 🛒 Guli Mall - Distributed Microservices E-Commerce System

**Project Overview**: Guli Mall is a high-availability, and high-performance B2C distributed e-commerce system. Built on **Spring Boot** and **Spring Cloud Alibaba** microservices architecture, it covers both the storefront (customer-facing) and the backend management system (operations). The project simulates real-world e-commerce scenarios, including product search, shopping cart, order processing, payment settlement, inventory management, marketing campaigns, and more.

## 📚 Table of Contents
- [Core Features](#core-features)
- [Tech Stack](#Tech Stack)
- [Project Structure](#project-structure)

##  ✨ Core Features
### Backend Management System(finish)
- Product Management: SPU/SKU management, brands, attribute groups, image uploads.
- Order Management: Order lists, shipment processing, refunds/after-sales.
- Inventory Management: Warehouse management, stock locking/unlocking, low-stock alerts.
- Marketing: Coupon distribution, points rules, flash sale configuration.
- Access Control: RBAC model, menu permissions, role assignment.
### Storefront (Customer-Facing, in developing)
- Homepage: Category navigation, new arrivals, flash sales.
- Product Search: Full-text search (Elasticsearch), filtering, and sorting(in developing).
- Shopping Process: Cart management, order confirmation, price calculation(in developing).
- User Center: Login/Registration (SSO), profile management, order tracking(in developing).
- Payment Integration: Alipay Sandbox, WeChat Pay demonstration(in developing).

## 🛠️ Tech Stack
| Technology | Description | Version |
| :--- | :--- | :--- |
| Spring Boot | Core development framework | 2.7.x |
| Spring Cloud Alibaba | All-in-one microservices solution | 2021.0.1.0 |
| Nacos | Service Discovery & Configuration Center | 2.x |
| OpenFeign | Declarative web service client | - |
| Sentinel | Traffic control, circuit breaking, degradation | - |
| Gateway | API Gateway, routing, filtering | - |
| Seata | Distributed transaction solution | - |
| MyBatis-Plus | ORM persistence framework | 3.5.x |
| Elasticsearch | Product search engine | 7.x |
| RabbitMQ | Message queue (decoupling, peak shaving) | - |
| Redis | Caching, distributed locks | - |
| OSS | Object Storage Service (MinIO) | - |
|flowable | Process engine | - |

## 📂 Module Structure

```text
gulimall
├── mall-common              # Common module: Utils, Constants, Exception Handling
├── mall-gateway             # Gateway Service: Routing, CORS, Rate Limiting
├── mall-modules             # Authentication Service: JWT, OAuth2
│   ├── mall-user            # User Service: User registration, login, profile management
│   ├── mall-product         # Product Service: SPU/SKU, Categories, Brands
│   └── mall-order           # Order Service: Order creation, Payment, State Machine
├── mall-external            # external module: Third-party services (Alipay, SMS, etc.)
├── mall-model               # Model Service: Datebase models, DTOs
└── mall-workflow            # Workflow Service: Process management, Task management for approval
```