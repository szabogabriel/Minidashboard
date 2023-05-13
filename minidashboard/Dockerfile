# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-alpine

WORKDIR /app

ENV DATA_DIR=/data

RUN mkdir /data

COPY target/minidashboard-0.0.1-SNAPSHOT.jar /app/minidashboard.jar

CMD ["java", "-Xmx64M", "-jar", "minidashboard.jar"]
