FROM openjdk:17-jdk-oracle

ARG JAR_FILE=target/gestion-examenes-backend.jar
COPY ${JAR_FILE} app-examenes.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-examenes.jar"]