FROM java:8-jre

ENV VERSION 2.26.3
ENV DIRECTORY /opt/wiremock

RUN mkdir $DIRECTORY
WORKDIR $DIRECTORY

RUN curl -sSL -o wiremock.jar https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/$VERSION/wiremock-standalone-$VERSION.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","wiremock.jar","--verbose", "--global-response-templating", "--port", "80"]