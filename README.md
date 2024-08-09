# Mobiauto's Vehicle Resale Management

---

## Introduction
The Mobiauto system aims to provide a powerful resale management tool
of vehicles. <br>
Below are the system's main features:<br><br>

#### Resale Management

Responsible for registering, searching for and deleting resales.<br>

#### Opportunity Management

Responsible for registering, updating, serching for and deleting opportunities.<br>

#### Opportunity Service

Responsible for assigning opportunities and for attending those opportunities.<br>

#### System Security

Responsible for authentication and authorization, ensuring requests are token secured and managing user data.<br>
<br>

---

## Overview
### Prerequisites

This system was designed to run in containers using [Docker](https://www.docker.com/get-started/).<br><br>
For the database, PostgreSQL was used with pgAdmin4 as the administrator.<br>
Each microservice requires its own datasource, e.g. the resale-management microservice needs a datasource with the name resale-management.<br><br>
Java version: Oracle OpenJDK version 17.0.7<br>
Maven version: 3.9.8<br>
Spring Boot version: 3.0.1<br>
Main libraries: Lombok, Spring Web, Spring Data JPA, Spring Security, Spring Validation, Spring HATEOAS and Spring Test.<br>
<br>

---

## Architecture
This application follows the microservices architecture and the RESTful pattern, so each microservice works as one deployable feature, providing its own endpoint with Hypermedia Controls, achieving the 4 levels of Richardson Maturity Model.<br>

#### resale-management
> Port: 8080<br>
> Responsible for registering, searching for and deleting resales.

#### opportunity-management
> Port: 8081<br>
> Responsible for registering, updating, serching for and deleting opportunities.
#### opportunity-service
> Port: 8083<br>
> Responsible for assigning opportunities and for attending those opportunities.
#### system-security
> Port: 8082<br>
> Responsible for authentication and authorization, ensuring requests are token secured and managing user data.
<br>

---

## Configuration
### First Run
At first run, the application creates a default user with ADMINISTRATOR role. <br>
To log in to the system and use the other features, you must use this endpoint with the following body: <br><br>
localhost:8082/api/v1/system-security/auth/authenticate<br>
```
{
    "username": "admin",
    "password": "password"
}
```
This should return a token for you to operate on the rest of the system.
<br><br>

### Dependencies
Each microservice has its own pom.xml file to list its own dependencies, but these are the most common in them: <br>
* org.projectlombok:lombok
* org.springframework.boot:spring-boot-starter-test
* org.springframework.boot:spring-boot-starter-web
* org.springframework.boot:spring-boot-starter-data-jpa
* org.springframework.boot:spring-boot-starter-hateoas
* org.springframework.boot:spring-boot-starter-validation
* org.postgresql:postgresql

### Environment Variables:
JAVA_HOME = Path to JDK, e.g. C:\Program Files\Java\jdk-17<br>
M2_HOME = Path to Maven, e.g. C:\Program Files\Maven<br>
<br>

---
## Contact
email: monnerat.mauricio@gmail.com








