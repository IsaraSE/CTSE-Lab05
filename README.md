# Microservices Lab: Polyglot System

This repository contains a complete Microservices system built with a polyglot approach (Java, Node.js, Python), dockerized for easy deployment.

## System Architecture

The system consists of four main components:
1.  **API Gateway** (Spring Cloud Gateway, Port 8080): Entry point for all requests.
2.  **Item Service** (Spring Boot - Java, Port 8081): Manages items.
3.  **Order Service** (Node.js + Express, Port 8082): Manages orders.
4.  **Payment Service** (Python + FastAPI, Port 8083): Processes payments.

## Prerequisites
- Docker
- Docker Compose

## Quick Start
To build and run all services:
```bash
docker-compose build
docker-compose up
```

## API Endpoints (via Gateway :8080)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/items` | List all items |
| `POST` | `/items` | Add a new item (`{"name": "Headphones"}`) |
| `GET` | `/orders` | List all orders |
| `POST` | `/orders` | Place an order (`{"item": "Laptop", "quantity": 1}`) |
| `GET` | `/payments` | List all payments |
| `POST` | `/payments/process` | Process a payment (`{"orderId": 1, "amount": 99, "method": "CARD"}`) |

## Project Structure
```text
.
├── api-gateway/       # Spring Cloud Gateway
├── item-service/      # Spring Boot
├── order-service/     # Node.js + Express
├── payment-service/   # Python + FastAPI
└── docker-compose.yml
```

## Lab Submission Details
**Course:** Current Trends in Software Engineering (SE4010)
**Module:** Microservices - Build, Dockerize, Deploy, Test
**Repository:** CTSE-Lab05
