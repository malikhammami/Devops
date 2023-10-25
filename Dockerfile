# Stage 1: Build the application 
FROM openjdk AS build
EXPOSE 8089
RUN chmod +x deployment-service
ADD target/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]

# Stage 2: Create the final image based on Alpine (Light wieght)
FROM alpine AS final
COPY --from=build /achat-1.0.jar /achat-1.0.jar
EXPOSE 8089
RUN chmod +x deployment-service
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]
