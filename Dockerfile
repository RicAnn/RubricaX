# Use OpenJDK with GUI support
FROM openjdk:21-jdk-slim

# Install required packages for GUI
RUN apt-get update && apt-get install -y \
    libxtst6 \
    libxrandr2 \
    libasound2 \
    libpangocairo-1.0-0 \
    libatk1.0-0 \
    libcairo2 \
    libgtk-3-0 \
    libgdk-pixbuf2.0-0 \
    libxss1 \
    libgconf-2-4 \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy source files
COPY src/ src/
COPY MANIFEST.MF .

# Compile and package
RUN javac -d bin src/*.java && \
    jar cfm RubricaX.jar MANIFEST.MF -C bin .

# Set display for X11 forwarding
ENV DISPLAY=:0

# Run the application
CMD ["java", "-jar", "RubricaX.jar"]