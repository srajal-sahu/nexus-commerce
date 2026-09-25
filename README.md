# 🛒 NexusCommerce — Enterprise E-Commerce Platform Backend

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-blue.svg)](https://spring.io/projects/spring-data-jpa)
[![Hibernate](https://img.shields.io/badge/Hibernate-7.4-darkblue.svg)](https://hibernate.org/)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0-green.svg)](https://swagger.io/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

> A production-grade, highly scalable E-Commerce backend platform built with **Spring Boot 4.1.1** (Spring Framework 7) and **Java 21**, engineered using clean layered architecture, dynamic JPA specifications, robust concurrency controls, and real payment gateway integration.

---

## 🏛️ Architectural Overview

NexusCommerce is built following clean enterprise architecture principles:

`
src/main/java/com/nexus/commerce/
├── common/         # Generic API response wrappers, constants, base records
├── config/         # Security, OpenAPI/Swagger, Redis, Web MVC configurations
├── controller/     # REST Controllers with RFC 7807 problem details and validation
├── domain/         # JPA Entities, Enums, and Audit base entities
├── dto/            # Immutable Java 21 Records for Request/Response payloads
├── exception/      # Domain exceptions and @RestControllerAdvice Global Handler
├── repository/     # Spring Data JPA Repositories & Dynamic Criteria Specifications
└── service/        # Business logic, transactional boundaries (@Transactional), caching
`

---

## ✨ Key Enterprise Capabilities

- [x] **Project Foundation:** Spring Boot 4.1.1 on Java 21 LTS with Maven Wrapper.
- [x] **Interactive Documentation:** OpenAPI 3 & Swagger UI at /swagger-ui.html.
- [x] **Health & Monitoring:** Spring Boot Actuator endpoints enabled.
- [ ] **Dynamic Catalog Search:** Multi-criteria filtering (price range, category, stock, text query) and multi-field sorting using JpaSpecificationExecutor.
- [ ] **Security & RBAC:** Stateless JWT authentication with refresh tokens and role-based authorization (ROLE_CUSTOMER, ROLE_ADMIN).
- [ ] **High-Concurrency Inventory:** Pessimistic locking (PESSIMISTIC_WRITE) to prevent overselling race conditions.
- [ ] **Order State Machine:** Explicit order lifecycle transitions (CREATED -> PENDING_PAYMENT -> PAID -> PROCESSING -> SHIPPED -> DELIVERED).
- [ ] **Payment Gateway:** Real payment processing (Stripe / Razorpay test mode) with webhook verification.
- [ ] **Reviews & Ratings:** Verified-purchase constraint and running average calculation.
- [ ] **High Performance Caching:** Redis caching with targeted cache invalidation.
- [ ] **Admin Analytics:** Revenue statistics, order status breakdowns, and bestselling product reports.

---

## 🚀 Getting Started

### Prerequisites
* **Java 21 LTS** or higher
* Git

### Running Locally
`ash
# Clone the repository
git clone <your-repository-url>
cd nexus-commerce

# Run using the included Maven Wrapper (no global Maven installation required)
./mvnw clean spring-boot:run        # Linux/macOS
.\mvnw.cmd clean spring-boot:run    # Windows PowerShell
`

### Access Points
* **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **OpenAPI JSON Spec:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)
* **H2 Database Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  * JDBC URL: jdbc:h2:mem:nexusdb
  * Username: sa
  * Password: *(leave empty)*
* **Actuator Health Probe:** [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
* **Service Ping:** [http://localhost:8080/api/v1/ping](http://localhost:8080/api/v1/ping)

---

## 📜 Git Commit Conventions

This project strictly follows the [Conventional Commits](https://www.conventionalcommits.org/) specification:
* eat: A new user-facing feature or domain capability
* ix: A bug fix
* docs: Documentation updates
* efactor: Code changes that neither fix a bug nor add a feature
* 	est: Adding or updating unit/integration tests
* chore: Build process, dependency updates, or tool configurations
