FROM gradle:8.14-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar notification_service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "notification_service.jar"]