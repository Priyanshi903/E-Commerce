FROM openjdk:8-jdk-alpine
WORKDIR usr/src
COPY ./target/authorization-service.jar ./
ENTRYPOINT ["java","-jar","authorization-service.jar"]