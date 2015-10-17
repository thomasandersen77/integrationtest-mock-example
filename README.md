# Description

This project is simple Spring application with a few JAX-RS enpoints. It combines Docker, Docker Compose and Wiremock to demonstrate how you can use this technique to mock out your integrations and take controll over your testdata.

## Pre requisits
Linux! And you need to have Docker, Docker Compose, Maven 3.x, Java 8 installed on your system to run this. They are all easy to Google, but in any case, here is a link: https://docs.docker.com/installation :) 

You should also install the Lombok plugin for your IDE or else you will get compiler errors.

## Project layout

The project consists of:
-  A Maven module which acts as a API over a few wiremock integrations. It generates a fat-jar wich is added to a Docker image (see Dockerfile under the rest-services directory) .
-  A directory called integrationmock with a Dockerfile that download and fire up wiremock.
-  A mockdata directory which contains directories and Json-files that defines the integration endpoints and data.
-  A `docker-compose.yml` that links the rest-service project with wiremock. It also maps a volume to the mockdata directory so you can have your testdata under source control. 

## Run project

Navigate to the root and run `mvn install` to generate rest-services.jar into target. Now you will the Jar-file that will be added to the docker image.

Now you can run `docker-compose up`. This will start both the Spring application named `rest-services` and Wiremock with testdata preloaded from the `mockdata`-directory. Wiremock recursivly loads all *.json files and exposes an endpoint as defined in the request section of the json-file.

You should now be able to use for example the Postman Chrome plugin to test both the intgrations and the JAX-RS endpoints: 

### Example of to test Test Wiremock

Try to do a GET for `http://localhost:18080/api/customer/123456789`.

The result should be a Json object like this:
```
{
      "firstname": "thomas",
      "lastname" : "andersen",
      "ssn": "123456789",
      "address" :
        {
          "street" : "street",
          "number" : 1,
          "city" : "Drammen"
        }
}
```

### Run or debug the Spring application inside an IDE with mock integrations

I case you want to run or debug the Spring application from your IDE, just run the class `AppMain.java`. You can start Wiremock with the command `docker-compose up wiremock` so the integrations will be availible. `wiremock` is the service name defined for the inside `docker-compose.yml`.   

Feel free to fork the project and take it for a spin.
