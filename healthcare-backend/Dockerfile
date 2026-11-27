# Build Stage
FROM maven:3.9.11-eclipse-temurin-25 AS build

WORKDIR /app

# Configure Google Maven Central mirror
COPY settings.xml /root/.m2/settings.xml

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 9966

ENTRYPOINT ["java", "-jar", "app.jar"]
