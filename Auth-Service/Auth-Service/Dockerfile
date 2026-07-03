# Stage 1: Build the Maven application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Optimize Java memory flags to prevent free-tier crashes
ENV MAVEN_OPTS="-Xmx512m -XX:MaxMetaspaceSize=128m"

COPY Auth-Service/Auth-Service/pom.xml .
COPY Auth-Service/Auth-Service/src ./src

# Added -B (batch mode) and concurrent threads configuration to save high memory footprint
RUN mvn clean package -B -DskipTests -T 1C

# Stage 2: Run the application with lightweight Java Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]
