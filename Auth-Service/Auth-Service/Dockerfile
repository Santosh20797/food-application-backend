FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]


#Frontend (React)
#    ↓
#Cloud Run
#    ↓
#API/Microservices
#
#Auth Service        → Cloud Run
#Order Service       → Cloud Run
#Menu Service        → Cloud Run
#Item Service        → Cloud Run
#Payment Service     → Cloud Run
#Delivery Service    → Cloud Run
#
#PostgreSQL          → Cloud SQL
#
#Kafka               → Confluent Cloud
#                     (easier than self-managing Kafka on GCP)