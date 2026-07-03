# Stage 1: Build the Maven application using a pre-packaged Maven image
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml from the Auth-Service directory
COPY Auth-Service/Auth-Service/pom.xml .
# Copy the src directory from the Auth-Service directory
COPY Auth-Service/Auth-Service/src ./src

# Build the package
RUN mvn clean package -DskipTests

# Stage 2: Run the application with a lightweight Java Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
