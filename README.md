# Hello BitBucket

This is a sample project that runs a Spring Boot application
with a bit of the essentials on a REST API -- a Controller
that contains endpoints, a Model (and DAO) for the data, and a
Service that provides the data (for now. Repositories usually do
this.

## How to Run

Have a working Java and Maven environment and `git clone` this project
to your computer.

Use `mvn clean install` to start things up.

Use `mvn spring-boot:run` to start the server. You can perform calls to `http://localhost:8080/announce`
and should see a list of users.

Use `mvn test` to run unit tests.


