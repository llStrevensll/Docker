FROM openjdk:8-jdk-alpine

WORKDIR /home/app
EXPOSE 8080

COPY /target/*.jar /home/app/application.jar

ENTRYPOINT ["sh", "-c", "java -jar application.jar"]
