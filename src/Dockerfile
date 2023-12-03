# Use a base image with Java and Maven
FROM maven:4.0.0-openjdk-19

# Set the working directory in the Docker container
WORKDIR /app

# Copy the pom.xml file first to download dependencies
COPY pom.xml .

# Download dependencies (this can be cached to speed up subsequent builds)
RUN mvn dependency:go-offline

# Copy the rest of your project files into the Docker container
COPY src /app/src

# Compile and package the application
# If tests are to be skipped during build, uncomment the next line
# RUN mvn clean package -Dmaven.test.skip=true
RUN mvn clean package

# Command to execute tests
CMD ["mvn", "test"]
