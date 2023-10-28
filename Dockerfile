FROM openjdk:11-jre-slim
EXPOSE 8089
WORKDIR /app

# Download the JAR file from Nexus and copy it into the container
RUN apt-get update && apt-get install -y curl
RUN curl -o achat-1.0.jar -L "http://192.168.1.222:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
