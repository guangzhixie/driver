# Driver Service

This project is a simple application for drivers to update their locations, and for users to find drivers nearby.

## Overview of tech stack 

- **Language**

    - Java

- **Web Framework**

    - Spring Boot

    - JPA

    - Lombok

- **Database**

    - MySQL for production

    - H2 for integration test

- **Caching**

    - Google Guava Cache

- **Build**

    - Gradle

- **Test**

    - Cucumber

    - Mockito


## Brief description of chosen tech stack

**Java + Spring Boot + Gradle** is a popular stack for fast deliver stand-alone, production-grade application. **Mockito** makes it easy to unit-test the services with dependency injections. **Cucumber** test framework implements the BDD concept to make sure the application is working as expected. **Lombok** allows us to write much lesser code when creating models. **JPA** provides a convenient way for DB related operations.

**MySQL** is an open-source relational database management system that is able to accomodate DB requirements in this application, while **H2** provides an in-memory DB to be used in the integration test.

**Google Guava Cache** is a high-performance cache, which is used in this application to cache all drivers' real-time locations, for read and write.


## Infrastructure requirements

- JDK 1.8
- Docker
- Docker Compose