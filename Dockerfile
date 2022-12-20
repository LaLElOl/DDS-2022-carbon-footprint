FROM openjdk:8-jdk-alpine
WORKDIR /2022-mi-no-mino-grupo-12


COPY target/grupo-12-1.0-SNAPSHOT-jar-with-dependencies.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9000
