# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:mysql://sql7.freesqldatabase.com:3306/sql7713489
ENV SPRING_DATASOURCE_USERNAME=sql7713489
ENV SPRING_DATASOURCE_PASSWORD=2JQHxTsHfD

WORKDIR /app

# Copy the application JAR file into the container
COPY target/experiment-0.0.1-SNAPSHOT.jar /app/experiment-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "experiment-0.0.1-SNAPSHOT.jar"]

