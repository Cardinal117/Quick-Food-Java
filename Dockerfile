# Base image with OpenJDK
FROM openjdk:17-slim

# Set working directory inside the container
WORKDIR /app

# Copy all .java files into the container's working directory
COPY . .

# Compile all Java files
RUN javac *.java

# Run the main class
CMD ["java", "Main"]
