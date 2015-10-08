FROM java:8

ENV WM_PKG_NAME wiremock
ENV WM_VERSION 1.57

RUN \
  cd / && \
  wget https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock/$WM_VERSION/$WM_PKG_NAME-$WM_VERSION-standalone.jar && \
  mkdir /$WM_PKG_NAME && mv $WM_PKG_NAME-$WM_VERSION-standalone.jar /$WM_PKG_NAME/$WM_PKG_NAME.jar

WORKDIR /integrationmock

# start wiremock
ENTRYPOINT exec java -jar wiremock.jar


# Expose port
EXPOSE 8080