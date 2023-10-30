FROM openjdk
EXPOSE 8089
RUN apt-get update && apt-get install -y curl
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
