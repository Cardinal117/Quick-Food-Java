FROM openjdk:17-jdk-slim
WORKDIR /app
COPY myapp.jar app.jar
CMD ["java", "-jar", "app.jar"]
