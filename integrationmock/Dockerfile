FROM java:8

ENV WM_PACKAGE wiremock
ENV WM_VERSION 1.57

RUN mkdir /$WM_PACKAGE
WORKDIR /$WM_PACKAGE
RUN curl -sSL -o $WM_PACKAGE.jar https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock/$WM_VERSION/$WM_PACKAGE-$WM_VERSION-standalone.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","wiremock.jar"]

