# Driver Service

This project is a simple application for drivers to update their locations, and for users to find drivers nearby.

## Overview of Tech Stack 

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

- **Deployment**

    - Docker

    - Docker Compose


## Tech Stack Descriptions

**Java + Spring Boot + Gradle** is a popular stack for fast deliver stand-alone, production-grade application. **Mockito** makes it easy to unit-test the services with dependency injections. **Cucumber** test framework implements the BDD concept to make sure the application is working as expected. **Lombok** allows us to write much lesser code when creating models. **JPA** provides a convenient way for persistence operations.

**MySQL** is an open-source relational database management system that is able to accomodate DB requirements in this application, while **H2** provides an in-memory DB to be used in the integration test.

**Google Guava Cache** is a high-performance cache, which is used in this application to cache all drivers' real-time locations, for read and write.

**Docker** enables us to easily pack, ship, and run the application as a lightweight, portable, self-sufficient container, which can run virtually anywhere. **Docker Compose** further helps to define and run multi-container Docker applications, in this case, the main web application and the MySQL database. They all help to simplify and accelerate the deployment and operation process.


## Infrastructure Requirements

- JDK 1.8
- Docker
- Docker Compose


## Setup Instructions

[run.sh](run.sh) is provided for easy build and deployment. Please make sure all the infrastructure requirements are satisfied before running the script.

To stop the application, please run the following command on the root directory:

```bash
docker-compose down
```

To bring it up again, please run the following command on the root directory:

```bash
docker-compose up
```

## Future Improvements

As I only have limited time at the weekend to do the actual coding, there are some points for future improvements (some of them are also marked as **TODO** in the code).

- **Performance**
    
    - A load test needs to be added.

    - Currently Guava cache should have no issue to host 50,000 entries of driver locations in memory in a single standard machine. However, if we want to scale out to have multiple instances running, we need to use **Hazelcast** or **Redis** as a distributed cache to replace the Guava cache. We also need to have **centralized config, centralized logging, service discovery, and load balancer** in place. The application itself is stateless so it will be fine in the microservice architecture.

    - The algorithm currently in use to find the nearby driver is very simple but inefficient. A more advanced algo like **GeoHash** should be used if we want to achieve a higher performance.

- **Security**
    
    - Need to use HTTPS for REST connection.

    - Each request needs to be authorized and authenticated.

    - Each request should have a signature.

- **Database**
    
    - Currently I only have one table to store each driver's latest location. The DB update process is async in the application to prevent the DB performance to block the main flow. Drivers' last locations will be preloaded into the cache when application boots up. We should add a table to store driver's basic information, and a table to store each driver's historical locations in order for data analysis.

    - Liquibase should be used to create and alter tables, for easy tracking.



