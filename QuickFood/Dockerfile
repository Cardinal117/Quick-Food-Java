# Use OpenJDK image
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy Java source files into the container
COPY src/ ./src/

# Compile the Java files
RUN javac src/*.java

# Set the entrypoint (change if package name exists)
CMD ["java", "-cp", "src", "Main"]
