# Stage 1: Build the Maven application
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Install Maven manually to bypass unstable base images
RUN apt-get update && apt-get install -y maven

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application with lightweight Java Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
