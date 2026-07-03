# Stage 1: Build the Maven application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Using asterisks ensures Linux matches the directory even with space configurations
COPY Auth-Service/Auth-Service/pom.xml .
COPY Auth-Service/Auth-Service/src ./src

# Build the jar package
RUN mvn clean package -DskipTests

# Stage 2: Run the application with lightweight Java Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
