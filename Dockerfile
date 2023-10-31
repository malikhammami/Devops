FROM openjdk:11-jre-slim
EXPOSE 8082
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o achat-1.0.jar -L "http://192.168.162.222:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"
ENTRYPOINT ["java","-jar","achat-1.0.jar"]
