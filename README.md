# Home 4 You API

This repository contains the source code and documentation for the Home 4 You API, a Java 17 Spring Boot 3 application.

## Requirements

To run the application, ensure that the following prerequisites are met:

- Java 17 or later is installed on your machine.
- Maven 3.9.0 or later is installed.
- Docker and Docker Compose are installed.

## Getting Started

Follow the steps below to set up and run the Home 4 You API:

1. Clone this repository to your local machine:

```shell
git clone https://github.com/your-username/home-4-you-api.git
```

2. Navigate to the project's root directory:

```shell
cd home-4-you-api
```

3. Build the project using Maven:

```shell
mvn clean install
```

4. Start the services using Docker Compose:
```shell
docker-compose up
```

This command will start all the necessary dependencies (such as databases) defined in the `docker-compose.yml` file.

5. Start the application with the following command:
```shell
mvn spring-boot:run
```
6. Once the application is up and running, you can access it at `http://localhost:8000`.

## Configuration

The application can be configured by modifying the `application.yml` file located in the `src/main/resources` directory. Update the necessary properties according to your environment or specific requirements.
