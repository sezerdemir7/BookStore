FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY target/BookStore-0.0.1-SNAPSHOT.jar bookstore.jar
ENTRYPOINT ["java","-jar","bookstore.jar"]