# Mobiauto's Vehicle Resale Management

## Introduction
The Mobiauto system aims to provide a powerful resale management tool
of vehicles. <br>
Below are the system's main features:<br><br>

#### Resale Management

The Resale Management feature is responsible for registering, searching for and deleting resales from the system.<br>

#### Opportunity Management

The Opportunity Management feature is responsible for registering, updating, serching for and deleting opportunities from the system.<br>

#### Opportunity Service

The Opportunity Service feature is responsible for assigning opportunities to users and for attending those opportunities.<br>

#### System Security

The System Security feature is responsible for authenticating users, ensuring token secured requests and managing user data.<br><br>

## Overview
### Prerequisites

This system was designed to run in containers using [Docker](https://www.docker.com/get-started/).<br><br>
For the database, PostgreSQL was used with pgAdmin4 as the administrator.<br>
The system requires a datasource for each microservice, e.g. the resale-management microservice needs a datasource with the name resale-management.<br><br>
In addition, Java 17 (Oracle OpenJDK version 17.0.7) and Maven (version 3.9.8) were used.<br>
Spring Boot version: 3.0.1<br>
Main libraries: Lombok, Spring Web, Spring Data JPA, Spring Security, Spring Validation, Spring HATEOAS and Spring Test.

### Architecture
This software was made using the microservices architecture, so each microservice works as one deployable feature of the system.<br>
Furthermore, this system follows the RESTful pattern, so each microservice provides its own endpoints with Hypermedia Controls, achieving the 3 levels of Richardson Maturity Model.<br><br>





