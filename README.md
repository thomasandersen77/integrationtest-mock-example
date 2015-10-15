# Integrationtest mock example

A simple Spring application demonstrating the power of docker, docker-compose and wiremock in combination.

## Pre requisits
Linux! And you need to have Docker, Docker Compose, Maven 3.x, Java 8 installed on your system to run this. They are all easy to Google, but in any case, here is a link: https://docs.docker.com/installation :) 

You should also install the Lombok plugin for IntelliJ or else you will get compiler errors.

## Run project

First you need to run 'mvn install' to generate rest-services.jar into target.

Navigate to the root of the project and give the command: 'docker-compose up'. This will start both the Spring application with REST endpoints and Wiremock with testdata preloaded from the 'mockdata'-directory. Wiremock recursivly loads all *.json files and exposes an endpoint as defined in the request section of the json-file.
You should be able to use for example the Chrome plugin Postman to test: 

Try to do a GET for http://localhost:18080/api/customer/123456789.

The result should be a Json object like this:
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

If you only want to start Wiremock, the command is 'docker-compose up wiremock' (this is the name defined for wiremock inside docker-compose.yml).

