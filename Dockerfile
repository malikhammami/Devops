
FROM openjdk:8-jdk-alpine


ARG NEXUS_URL=http://localhost:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/
ARG JAR_FILE=achat-1.0.jar

ADD ${NEXUS_URL}${JAR_FILE} springachat.jar


EXPOSE 8089

ENTRYPOINT ["java","-jar","/springachat.jar"]
