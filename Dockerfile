# Use a lightweight JDK image
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy all .java files from your source folder into the container
COPY ./src/ ./src/

# Compile all .java files
RUN javac src/*.java

# Run the main class
CMD ["java", "-cp", "src", "Main"]