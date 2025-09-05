# Social Benefits System "Үй-бүлөгө көмөк" (UBK)

This is a Spring Boot application for the social benefits system "Үй-бүлөгө көмөк".

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [API](#api)

---

### Features
- Application processing for social benefits.
- Benefit calculation and recalculation engine.
- Payment generation.
- Integration with external government services via Tunduk (X-Road).
- REST API with JWT security and Swagger documentation.
- Automated background tasks using a scheduler.

### Tech Stack
- **Backend:** Java 17, Spring Boot 3.x
- **Database:** PostgreSQL
- **Cache:** Redis
- **Build Tool:** Maven
- **API Docs:** Springdoc OpenAPI (Swagger)
- **Testing:** JUnit 5, Mockito, AssertJ, Testcontainers, WireMock

### Prerequisites
- Java 17
- Docker and Docker Compose

### Running the Application (Development)

The easiest way to run the application for development is by using the default Docker Compose setup, which uses the `dev` Spring profile.

1.  **Build the application JAR:**
    ```sh
    ./mvnw clean package
    ```

2.  **Start all services (App, PostgreSQL, Redis):**
    ```sh
    docker-compose up --build
    ```
    The application will be available at `http://localhost:8080`.

### Production Deployment

To deploy the application in a production-like environment, use the provided deployment script.

1.  **Navigate to the scripts directory:**
    ```sh
    cd scripts
    ```

2.  **Run the deployment script:**
    ```sh
    ./deploy.sh
    ```
    The first time you run this, it will create a `.env` file in the project root. You **must** fill this file with your production secrets (database password, JWT secret, etc.) before running the script again.

    The script will build a production-optimized Docker image and start the services using `docker-compose.prod.yml`.

3.  **Accessing the API Documentation:**
    Once the application is running, the interactive Swagger UI can be accessed at:
    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Running Tests

This project has a comprehensive test suite, including unit and integration tests.

1.  **Run all tests using Maven:**
    ```sh
    ./mvnw test
    ```
    This command will automatically:
    - Start and stop a PostgreSQL database using Testcontainers for database tests.
    - Start and stop a WireMock server for Tunduk integration tests.
    - Run all unit and integration tests.

### API

The API is secured with JWT. To interact with the secured endpoints, you must first get a token.

**Get a token:**
Send a POST request to `/api/auth/login` with the following body:
```json
{
  "username": "admin",
  "password": "password"
}
```

You will receive an `accessToken`. Include it in the `Authorization` header for all subsequent requests: `Authorization: Bearer <your_token>`.

For more details, see the [API Documentation](./docs/api/api-documentation.md).
