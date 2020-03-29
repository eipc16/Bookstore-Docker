FROM maven:3.6-jdk-13 AS MAVEN_BUILD
MAINTAINER Przemysław Pietrzak

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/

COPY pom.xml .
RUN mvn dependency:go-offline

RUN mvn package
FROM adoptopenjdk/openjdk13:alpine-jre

ENV JPDA_ADDRESS="*:8001"
ENV JPDA_TRANSPORT="dt_socket"

WORKDIR /app

# config tomcat, make app run as root
FROM tomcat:jdk13-openjdk-oracle
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=MAVEN_BUILD /build/target/proxy-service.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080 8001
ENTRYPOINT ["catalina.sh", "jpda", "run"]