# Inventory Management API

A RESTful inventory management backend built with **Java, Spring Boot, Spring Data JPA, and PostgreSQL**.

The application manages products and orders, including inventory tracking and business logic that automatically reduces product stock when an order is successfully placed.

This API is used by an **Angular frontend** to provide a full-stack inventory management application.

## Features

- Create products
- View all products
- View a product by ID
- Update products
- Delete products
- Create orders
- Automatically reduce inventory when an order is placed
- Prevent orders when inventory is insufficient
- Product input validation
- RESTful API architecture
- PostgreSQL database persistence
- CORS configuration for Angular frontend integration
- Service-layer business logic
- Unit testing with JUnit and Mockito

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Maven

### Database

- PostgreSQL

### Testing

- JUnit 5
- Mockito

### Frontend

The API is connected to a separate Angular frontend that provides the user interface for managing products and creating orders.

## Project Structure

```text
src/
├── main/
│   ├── java/com/alirasouli/inventory/
│   │   ├── controller/
│   │   ├── exception/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── InventoryApiApplication.java
│   └── resources/
└── test/
    └── java/com/alirasouli/inventory/
        └── service/
```

## API Endpoints

### Products

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get a product by ID |
| POST | `/api/products` | Create a product |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

### Orders

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/orders` | Create an order and reduce product inventory |

## Inventory Business Logic

When an order is submitted, the application:

1. Finds the requested product.
2. Checks whether enough inventory is available.
3. Rejects the order if the requested quantity exceeds available inventory.
4. Reduces the product quantity when the order is valid.
5. Saves the updated inventory and order to the database.

For example, if a product has `10` units available and an order requests `3`, the remaining inventory becomes `7`.

If an order requests more inventory than is available, the API rejects the request with an HTTP `409 Conflict` response.

## Example Product

```json
{
  "name": "Mechanical Keyboard",
  "description": "RGB mechanical keyboard",
  "price": 89.99,
  "quantity": 10
}
```

## Example Order

```json
{
  "productId": 1,
  "quantity": 2
}
```

## Running the Application

### Prerequisites

Install:

- Java 21
- PostgreSQL
- Maven, or use the included Maven Wrapper

Configure the PostgreSQL database connection in the application's configuration/environment variables.

Then run:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs locally on:

```text
http://localhost:8080
```

## Running Tests

On Windows:

```powershell
.\mvnw.cmd test
```

The project includes tests for application startup and order service business logic, including inventory reduction and insufficient-inventory behavior.

## Frontend

The Angular frontend communicates with this API and provides:

- Product listing
- Product creation
- Product editing
- Product deletion
- Order creation
- Automatic inventory updates after orders
- User-facing feedback for successful and rejected orders

The frontend runs locally on:

```text
http://localhost:4200
```

## What I Learned

This project provided hands-on experience with:

- Building REST APIs with Spring Boot
- Structuring applications with controller, service, repository, and model layers
- Implementing business logic in the service layer
- Working with Spring Data JPA and PostgreSQL
- Handling HTTP status codes and API errors
- Writing unit tests with JUnit and Mockito
- Connecting an Angular frontend to a Java backend
- Handling CORS between frontend and backend applications

## Author

**Ali Rasouli**