FROM maven:3.8.5-openjdk-17 as  dependencies

WORKDIR /opt/app
COPY pom.xml .
RUN mvn -B -e org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=domain

FROM  maven:3.8.5-openjdk-17 as builder

WORKDIR /opt/app
COPY --from=dependencies /root/.m2 /root/.m2
COPY --from=dependencies /opt/app/ /opt/app
COPY src src
RUN mvn -B -e clean install

FROM openjdk:17-jdk-alpine

WORKDIR  /opt/app
COPY --from=builder /opt/app/target/architecture-map-0.0.1-SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]
