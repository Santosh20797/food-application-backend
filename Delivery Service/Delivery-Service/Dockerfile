# Step 1: Build the menu-service application using Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source files from the menu-service folder
COPY pom.xml .
COPY src ./src

# Compile and package the application into a runnable JAR file
RUN mvn clean package -DskipTests

# Step 2: Create a lightweight runtime image to execute the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the compiled JAR file from the build step into this runtime step
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Required for Google Cloud Run default routing)
EXPOSE 8080

# Execute the Spring Boot application when the container launches
ENTRYPOINT ["java", "-jar", "app.jar"]
