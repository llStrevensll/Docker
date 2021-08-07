FROM openjdk:8-jdk-alpine

ARG DEPENDENCY=target/dependency

EXPOSE 8080


COPY ${DEPENDENCY}/BOOT-INF/lib app/lib
COPY ${DEPENDENCY}/META-INF app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#COPY /target/*.jar /home/app/application.jar


ENTRYPOINT ["java","-cp","app:app/lib/*","com.strevens.rest.webservices.restfulwebservices.RestfulWebServicesApplication"]
#ENTRYPOINT ["sh", "-c", "java -jar application.jar"]
