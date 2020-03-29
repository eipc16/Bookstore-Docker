FROM java:8-jre

ENV VERSION 1.58
ENV DIRECTORY /opt/wiremock

RUN mkdir $DIRECTORY
WORKDIR $DIRECTORY

RUN curl -sSL -o wiremock.jar https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock/$VERSION/wiremock-$VERSION-standalone.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","wiremock.jar","--verbose", "--port", "80"]