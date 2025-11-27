FROM maven:3.9-eclipse-temurin-21 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy
RUN groupadd --system --gid 1001 appgroup && \
    useradd --system --uid 1001 --gid 1001 appuser
USER appuser
WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]