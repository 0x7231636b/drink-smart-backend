# Use the official gradle image to create a build artifact.
# https://hub.docker.com/_/gradle
FROM gradle:jdk17 AS build

# Set the working directory in the image to "/app"
WORKDIR /app

# Copy the local code to the container's workspace.
COPY . .

# Build a release artifact.
RUN gradle clean build --no-daemon

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
FROM eclipse-temurin:21.0.2_13-jre-jammy

# Copy the jar to the production image from the builder stage.
COPY --from=build /app/build/libs/*.jar /app/app.jar

# # Create 'drinksmart' user
# RUN adduser --disabled-password --gecos '' drinksmart

# # Run the application under user "gradle" (non-root)
# USER drinksmart

# Run the web service on container startup.
CMD ["java", "-jar", "/app/app.jar"]