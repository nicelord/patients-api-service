## Requirements
- Java 11 (**important!**)
- Maven

## Note 

- This is an IntellijIdea Maven project, to avoid any missing configurations please import this project to IntellijIDEA as Maven Project
- To run in development : ```mvn spring-boot:run```
- Run test : ```mvn test```
- Build with dependencies: ```mvn clean package```
- Compiled output will be in : `target\patients-api-service-0.0.1-SNAPSHOT.jar` and ready to ship

## Run the app
- Start the app: `java -jar target\patients-api-service-0.0.1-SNAPSHOT.jar`
- App server will run in port 8080

## Solution decisions:
This project implements hexagonal architecture, follows DDD practice, clean code and SOLID principle, the purpose is to make this project extensible and easy to maintain.

The project currently bundled together in a single maven project, later when new requirements coming, some packages can be easily separated as an independent module or even an independent project 
since each layer (in this case packages) have a very small coupling with the other, such as the adapters and domain. Thus, we achieve modularity by implementing this pattern.


## Assumptions
With the given limited context, assumptions have been made:
 
- H2 in-memory database used for this demo purpose



<!-- CONTACT -->
## Contact

Riza Maizarani - [LinkedIn](https://www.linkedin.com/in/rizam/) - reja.mail@gmail.com
