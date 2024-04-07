FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /project

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /project/src

RUN mvn package

FROM eclipse-temurin:latest

RUN mkdir /app

COPY --from=build /project/target/Identiticoders-0.0.1-SNAPSHOT.jar /app/Identiticoders-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8081

CMD java $JAVA_OPTS -jar Identiticoders-0.0.1-SNAPSHOT.jar