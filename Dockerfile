# Start with a base image containing Java runtime
FROM openjdk:13-jdk-alpine

# Add Maintainer Info
LABEL maintainer="rajput.manoor@yahoo.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

COPY tinyurl-docker-0.1.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]