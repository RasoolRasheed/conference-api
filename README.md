# Conference Session Submission Microservice

## 🧩 Problem Statement

This microservice is part of a SaaS platform for managing conference presentations. The goal is to build a RESTful API using Dropwizard that allows speakers to submit, retrieve, update, and delete session proposals for a conference. The application stores session data in a MySQL database and ensures secure, performant, and scalable handling of session data.

## 🔧 Approach and Architectural Decisions

### Tech Stack
- **Framework**: [Dropwizard](https://www.dropwizard.io/en/latest/) (Java)
- **Database**: MySQL (accessed using JDBI3)
- **Authentication**: Basic API Key-based authentication
- **Containerization**: Docker
- **Testing**: JUnit and Mockito

### Key Components
- `SessionResource`: REST API endpoints
- `SessionDAO`: JDBI3 DAO interface for database operations
- `Session`: Domain model representing session data
- `ApiKeyAuthFilter`: Implements API key authentication
- `ConferenceApplication` & `ConferenceConfiguration`: Application bootstrap and configuration
- Logging via SLF4J and Logback

### Architecture Highlights
- Layered architecture separating resource, service, and data layers
- API key authentication implemented via Dropwizard’s auth filters
- MySQL queries optimized with indexes and connection pooling (via HikariCP)
- Docker used for packaging and environment consistency
- Input validation at the DTO level using Hibernate Validator

## 🚀 How to Run the Application

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker & Docker Compose (for DB and app)
- MySQL running locally or in Docker

### 1. Clone the Repo
```bash
git clone https://github.com/yourusername/conference-submission-service.git
cd conference-submission-service

### 2. Run the application
mvn clean package
java -jar target/conference-submission-service.jar server config.yml
