# Hotel Review API - Microservices in Spring Boot
This README provides an overview of the Hotel Review API, a microservices-based application built using Spring Boot. The API leverages various features and technologies, including Lombok, Resilience4j, Circuit Breaker, Rate Limiter, Retry, and OKTA for authentication and security. The API consists of three core services: User, Hotel, and Review, with User serving as the main entry point through the API gateway. Additionally, the API employs a Config Server for storing application configuration on a GitHub repository and utilizes Netflix Eureka for service registration and discovery. This document provides essential information to understand, set up, and use the Hotel Review API.

Table of Contents
1. Architecture
2. Features
3. Endpoints
4. Authentication and Security
5. Configuration
6. Service Discovery
7. Communication
8. Exception Handling

**Architecture**

The Hotel Review API follows a layered architecture, comprising the following components:
1. **Controller:** Handles incoming HTTP requests and defines API endpoints.
2. **Entity:** Represents data structures and entities used within the application.
3. **DTO (Data Transfer Object):** Facilitates data exchange between services and clients.
4. **Services:** Implements business logic and service operations.
5. **Repositories:** Manages data storage and retrieval.
6. **Config:** Centralizes application configuration using the Config package.
7. **Exception:** Handles exceptions and error responses.

**Features**

The Hotel Review API incorporates several key features and technologies:

1. **Lombok:** Reduces boilerplate code and simplifies entity classes.
2. **Resilience4j:** Provides resilience features like circuit breakers, rate limiters, and retries to enhance system robustness.
3. **OKTA Integration:** Secures endpoints and enables JWT-based user authentication.
4. **Service Registry (Netflix Eureka):** Simplifies service registration and discovery.
5. **Config Server:** Centralizes and manages configuration settings via GitHub.
6. **Feign Client and RestTemplate:** Facilitates communication between microservices.
7. **Layered Architecture:** Organizes codebase into well-defined layers for maintainability and scalability.

**Endpoints**

The API offers various endpoints to perform operations related to hotels, reviews, and users. Detailed documentation of these endpoints can be found in the API documentation, which should be provided separately.

**Authentication and Security**

The Hotel Review API is secured using OKTA and JWT (JSON Web Tokens). All endpoints require authentication using JWT tokens obtained through the OKTA authentication service. Access to internal services is also secured to prevent unauthorized access.

**Configuration**

Application configuration is centralized and stored on a GitHub repository, managed by the Config Server. This allows for easy maintenance and updating of configuration settings.

**Service Discovery**

Service discovery is facilitated through Netflix Eureka, enabling microservices to register themselves and discover other services within the ecosystem. This dynamic service discovery simplifies communication between services.

**Communication**

The API utilizes Feign Client and RestTemplate to enable communication between microservices. These components allow services to make HTTP requests to one another in a reliable and efficient manner.

**Exception Handling**

The Hotel Review API incorporates robust exception handling to ensure graceful error responses. Exception handling is implemented at the controller level to provide meaningful error messages to clients.



Thank you for using the Hotel Review API.
