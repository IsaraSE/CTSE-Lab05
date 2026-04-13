
# CTSE-Lab05: Polyglot Microservices System

[![Docker](https://img.shields.io/badge/Docker-Enabled-blue?logo=docker&logoColor=white)](https://www.docker.com/)
[![Java](https://img.shields.io/badge/Language-Java%2017-orange?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Node.js](https://img.shields.io/badge/Language-Node.js-green?logo=nodedotjs&logoColor=white)](https://nodejs.org/)
[![Python](https://img.shields.io/badge/Language-Python%203.9-blue?logo=python&logoColor=white)](https://www.python.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A robust, polyglot microservices ecosystem designed for the **Current Trends in Software Engineering (SE4010)** lab. This project demonstrates service-to-service communication, API gateway routing, and container orchestration using Java, Node.js, and Python.

---

## 🏗️ System Architecture

The architecture follows a modular approach where each service is built with a different technology stack, communicating through a unified **Spring Cloud Gateway**.

```mermaid
graph TD
    User([User Client]) -->|Port 8080| GW[API Gateway - Spring Cloud]
    
    subgraph "Microservices Layer"
        GW -->|/items| IS[Item Service - Java/Spring]
        GW -->|/orders| OS[Order Service - Node.js/Express]
        GW -->|/payments| PS[Payment Service - Python/FastAPI]
    end
    
    IS --- |"Internal State"| IS_DB[(In-Memory)]
    OS --- |"Internal State"| OS_DB[(In-Memory)]
    PS --- |"Internal State"| PS_DB[(In-Memory)]

    style GW fill:#f9f,stroke:#333,stroke-width:2px
    style IS fill:#ffa500,stroke:#333,stroke-width:2px
    style OS fill:#2ecc71,stroke:#333,stroke-width:2px
    style PS fill:#3498db,stroke:#333,stroke-width:2px
```

---

## 🛠️ Technology Stack

| Service | Technology | Port | Description |
| :--- | :--- | :--- | :--- |
| **API Gateway** | Spring Cloud Gateway | `8080` | Central entry point and request router. |
| **Item Service** | Java 17 + Spring Boot | `8081` | Manages product inventory and item details. |
| **Order Service** | Node.js + Express | `8082` | Handles order creation and tracking. |
| **Payment Service** | Python 3.9 + FastAPI | `8083` | Processes transactions and payment logs. |

---

## 🚀 Getting Started

### Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running.
- [Docker Compose](https://docs.docker.com/compose/install/) (v2.0+ recommended).

### Deployment
1. **Clone the repository:**
   ```bash
   git clone https://github.com/IsaraSE/CTSE-Lab05.git
   cd CTSE-Lab05
   ```

2. **Build and Run:**
   ```bash
   docker-compose up --build
   ```

3. **Verify Health:**
   Check if services are alive via the Gateway:
   - `GET http://localhost:8080/items/health`
   - `GET http://localhost:8080/orders/health`
   - `GET http://localhost:8080/payments/health`

---

## 📖 API Documentation

### 1. Item Service (`/items`)
- **GET `/items`**: List all available items.
- **POST `/items`**: Add a new item.
  - Body: `{"name": "Smartphone"}`
- **GET `/items/{id}`**: Retrieve specific item.

### 2. Order Service (`/orders`)
- **GET `/orders`**: View all orders.
- **POST `/orders`**: Place a new order.
  - Body: `{"item": "Laptop", "quantity": 2}`
- **GET `/orders/{id}`**: Get order status.

### 3. Payment Service (`/payments`)
- **GET `/payments`**: List all payment records.
- **POST `/payments/process`**: Process a new payment.
  - Body: `{"orderId": 1, "amount": 150.0, "method": "CREDIT_CARD"}`

---

## 📁 Project Structure

```text
.
├── api-gateway/         # Spring Cloud Gateway configuration
├── item-service/        # Java Spring Boot - Item management
│   └── src/main/java/   # Source code with SLF4J logging
├── order-service/       # Node.js Express - Order processing
├── payment-service/     # Python FastAPI - Payment gateway
├── docker-compose.yml   # Multi-container orchestration
└── README.md            # Comprehensive documentation
```

---

## 🎓 Lab Submission Details

- **Course**: Current Trends in Software Engineering (SE4010)
- **Lab Number**: 05
- **Focus**: Polyglot Architecture & Containerization
- **Student ID**: IT22154880

---

<p align="center">
  Developed with ❤️ for the CTSE Lab
</p>
