# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/minidashboard-0.0.1-SNAPSHOT.jar /app/minidashboard.jar

# Expose port 8080 for the Spring Boot app
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "minidashboard.jar"]
