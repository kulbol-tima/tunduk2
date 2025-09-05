# API Documentation

This document provides a high-level overview of the REST API for the "Үй-бүлөгө көмөк" (UBK) system. For detailed, interactive documentation, please run the application and navigate to `/swagger-ui.html`.

## Authentication

The API is secured using JWT (JSON Web Tokens). To access protected endpoints, you must first obtain a token by sending a POST request to `/api/auth/login`.

**Request:**
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Include this `accessToken` in the `Authorization` header of subsequent requests as a Bearer token:
`Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...`

## Main Endpoints

### Applications (`/api/applications`)
- `POST /`: Submit a new benefit application.
- `GET /{id}`: Retrieve an application by its ID.
- `PUT /{id}/status`: Update the status of an application.
- `GET /search`: Search for applications (e.g., by PIN).

### Recipients (`/api/recipients`)
- `GET /`: Get a paginated list of all benefit recipients.
- `GET /{id}`: Retrieve a specific recipient by ID.
- `PUT /{id}/recalculate`: Trigger a benefit recalculation for a recipient.

### Payments (`/api/payments`)
- `GET /`: Get a paginated list of all payments.
- `POST /generate`: Generate payment orders for a given period.
- `GET /reports`: (Placeholder) Get reports on payments.
