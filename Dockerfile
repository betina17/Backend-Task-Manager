# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/experiment_mpp
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=admin

WORKDIR /app

# Copy the application JAR file into the container
COPY target/experiment-0.0.1-SNAPSHOT.jar /app/experiment-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "experiment-0.0.1-SNAPSHOT.jar"]

