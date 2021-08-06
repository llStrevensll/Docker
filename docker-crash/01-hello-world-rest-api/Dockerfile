FROM openjdk:8-jdk-alpine

ARG DEPENDENCY=target/dependency

WORKDIR /home/app
EXPOSE 8080

COPY ${DEPENDENCY}/BOOT-INF/lib /home/app/lib
COPY ${DEPENDENCY}/META-INF /home/app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /home/app
COPY /target/*.jar /home/app/application.jar

ENTRYPOINT [ "java","-cp","app:home/app/lib/*","com.strevens.rest.webservices.restfulwebservices" ]
#ENTRYPOINT ["sh", "-c", "java -jar application.jar"]
