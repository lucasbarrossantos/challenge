## Guidelines for running the application

### About:
* API to manage users

This API has a start of `docker-compose` so basically it's necessary Docker to be running than Spring will start the `compose.yaml` file

Or if you are using Java 17 or higher you can execute the command below with gradle:

```gradle
gradle clean build
``` 

And then execute:

```ssh
java -jar build/libs/app.war
```

To carry out the tests there is a collection: `Challenge.postman_collection.json` in the root of the project that can be imported into the postman app

### Technologies I used in this project
- Java 17
- Spring Boot 3
- DSG Framework 
- Spring Data
- GraphQL
- Docker Compose (From Spring)