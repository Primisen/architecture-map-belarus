FROM openjdk:17-jdk-alpine
COPY target/architecture-map-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]