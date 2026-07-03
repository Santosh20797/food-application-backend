# Stage 1: Build the Maven application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Safe low-memory arguments for multi-module maven isolation
ENV MAVEN_OPTS="-Xmx384m -XX:MaxMetaspaceSize=128m"

# Copy the entire root folder context to map dependencies cleanly
COPY . .

# 🚀 FIXED COMMAND: Explicitly builds only the Auth-Service and skips all other heavy projects
RUN mvn clean package -B -DskipTests -pl Auth-Service/Auth-Service -am

# Stage 2: Run the application with a lightweight Java Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/Auth-Service/Auth-Service/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]
