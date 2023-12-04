FROM openjdk:19
EXPOSE 8080
ADD target/docker-integration.jar docker-integration
ENTRYPOINT["java", "-jar", "docker-integration.jar"]