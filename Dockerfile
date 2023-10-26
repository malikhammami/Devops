# Use a specific version of OpenJDK as the base image
FROM openjdk:11-jre-slim

# Expose the port your Spring Boot application uses
EXPOSE 8089

# Create a directory for your application
WORKDIR /app

# Copy the JAR file into the container
COPY tn/esprit/rh/achat/1.0/achat-1.0.jar achat-1.0.jar

# Set the command to run your Spring Boot application
CMD ["java", "-jar", "achat-1.0.jar"]
