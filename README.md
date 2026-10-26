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
## Account API

### Create Account

```http
POST /api/v1/accounts
Content-Type: application/json
```

Request:

```json
{
  "ownerName": "Alice",
  "currency": "CNY"
}
```

Response:

```json
{
  "id": "generated-uuid",
  "ownerName": "Alice",
  "balance": 0,
  "currency": "CNY"
}
```

### Get Account

```http
GET /api/v1/accounts/{id}
```

The `{id}` value is returned when an account is created.
### List Accounts

```http
GET /api/v1/accounts
```

Response:

```json
[
  {
    "id": "generated-uuid",
    "ownerName": "Alice",
    "balance": 0,
    "currency": "CNY"
  }
]
```