# Step 1: Use a lightweight JDK
FROM openjdk:17-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy your JAR file into the container
COPY target/*.jar app.jar

# Step 4: Expose the backend port
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
