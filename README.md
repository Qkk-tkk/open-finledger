# OpenFinLedger

A learning project for building a fintech backend service with Java and Spring Boot.

## Current Features

- Health check REST API
- Java 17
- Spring Boot
- Maven
- Git version control

## API

### Health Check

```http
GET /api/v1/health
```

Response:

```json
{
  "status": "ok",
  "service": "open-finledger"
}
```