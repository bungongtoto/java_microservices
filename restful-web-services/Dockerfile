# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/in28minutes/rest/webservices/restful_web_services/RestfulWebServicesApplication.java /home/app/src/main/java/com/in28minutes/rest/webservices/restful_web_services/RestfulWebServicesApplication.java

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

# Stage 2: Create the slim runtime image
FROM eclipse-temurin:21-jre-jammy
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]