# Step 1: Build the application using Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application using OpenJDK
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/CodeReviewPlatform-1.0-SNAPSHOT.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]