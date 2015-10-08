FROM java:openjdk-8-jdk

WORKDIR /source

ADD target/rest-services.jar     /source/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "rest-services.jar"]

#ENTRYPOINT ["mvn", "spring-boot:run"]