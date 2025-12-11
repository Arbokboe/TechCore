FROM gradle:8.14-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]