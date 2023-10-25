# Stage 1: Build the application 
FROM openjdk:11 AS build
EXPOSE 8089
ADD target/achat-1.0.jar achat-1.0.jar
CMD ["sh", "-c", "-jar", "/achat-1.0.jar"]
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]

# Stage 2: Create the final image based on Alpine (Light wieght)
FROM alpine AS final
COPY --from=build /achat-1.0.jar /achat-1.0.jar
EXPOSE 8089
CMD ["sh", "-c", "-jar", "/achat-1.0.jar"]
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]
