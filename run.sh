#!/bin/bash

# Define the path to the jar and the Maven project
JAR_PATH="./target/advance-technical-assessment-1.0-SNAPSHOT.jar"
MAVEN_PROJECT_PATH="."

# Check if the jar file exists
if [ ! -f "$JAR_PATH" ]; then
  echo "Jar file does not exist. Building with Maven..."
  cd "$MAVEN_PROJECT_PATH"
  ./mvnw clean package

  # Check if Maven build was successful
  if [ $? -ne 0 ]; then
    echo "Maven build failed!"
    exit 1
  fi
fi

# Run the jar file
echo "Running the jar file..."
java -jar "$JAR_PATH"
