
FROM maven:3.8.3-jdk-11 AS builder
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline
COPY src/ src/
RUN --mount=type=cache,target=/root/.m2 mvn package
FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
EXPOSE 8085
COPY --from=builder /app/target/achat-1.0.jar achat-1.0.jar
#ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
ENTRYPOINT ["java","-Djdk.tls.client.protocols=TLSv1.2","-jar","/achat-1.0.jar"]
