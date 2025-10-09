# Use Eclipse Temurin (more secure and maintained alternative)
FROM eclipse-temurin:21-jdk-jammy

# Install required packages for GUI applications
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
    xvfb \
    && rm -rf /var/lib/apt/lists/* \
    && apt-get clean

# Create non-root user for security
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set working directory
WORKDIR /app

# Copy source files and change ownership
COPY --chown=appuser:appuser src/ src/
COPY --chown=appuser:appuser MANIFEST.MF .

# Switch to non-root user
USER appuser

# Compile and package
RUN javac -d bin src/*.java && \
    jar cfm RubricaX.jar MANIFEST.MF -C bin .

# Create display script for X11 forwarding
RUN echo '#!/bin/bash\nif [ -z "$DISPLAY" ]; then\n    export DISPLAY=:99\n    Xvfb :99 -screen 0 1024x768x24 &\nfi\njava -jar RubricaX.jar' > start.sh && \
    chmod +x start.sh

# Expose port for potential web interface (future feature)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD pgrep -f "java.*RubricaX" > /dev/null || exit 1

# Run the application
CMD ["./start.sh"]