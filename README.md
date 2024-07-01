# Party Organizer Backend

This is the backend for the Party Organizer application built with Spring Boot, Spring Data JPA, and PostgreSQL.

## Getting Started

### Prerequisites

- Java 11 or later
- Maven
- PostgreSQL

### Installation

1. Clone the repository:


2. Update the application.properties file with your PostgreSQL configuration:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### API Endpoints

- `POST /api/auth/login` - User login
- `POST /api/auth/signup` - Register a new user
- `GET /api/parties` - Get list of parties (with pagination)
- `POST /api/parties/create` - Create a new party
- `PUT /api/parties/update/{id}` - Update a party
- `DELETE /api/parties/delete/{id}` - Delete a party
- `POST /api/messages/send` - Send a message
- `GET /api/messages/{partyId}/{recipientId}` - Get messages between users
- `POST /api/participants` - Register a participant
- `GET /api/participants` - Get list of participants (with pagination)
- `POST /api/ratings` - Add a rating
- `GET /api/ratings` - Get list of ratings (with pagination)

### Access Identifiers

Use the following credentials to access the application:

- Username: `john@example.com`
- Password: `password`

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Data persistence
- [PostgreSQL](https://www.postgresql.org/) - Database

