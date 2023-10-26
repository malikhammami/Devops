# Stage 1: Build the application
FROM openjdk:11 AS build
WORKDIR /app
COPY target/achat-1.0.jar achat-1.0.jar
RUN jar -xf achat-1.0.jar

# Stage 2: Create the final image based on Alpine (Lightweight)
FROM alpine AS final
WORKDIR /app
COPY --from=build /app/ .
EXPOSE 8089
CMD ["java", "-jar", "achat-1.0.jar"]

