# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source files and build the app
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]
