# Use OpenJDK 11 as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled .jar file from the host to the container
COPY target/conference-1.0-SNAPSHOT.jar /app/conference-service.jar

# Expose the port the app will run on (8080 in this case)
EXPOSE 8080

# Set environment variables (optional, if needed)
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the Dropwizard application
ENTRYPOINT ["java", "-jar", "conference-service.jar", "server", "config.yml"]
