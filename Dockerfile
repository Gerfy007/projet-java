# Dockerfile - Alpine version with dev tools

# Use lightweight Alpine Linux as base image
FROM alpine:latest

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Install common tools: bash, curl, git, nano
RUN apk update && \
    apk add --no-cache bash curl git nano

# Optional: set environment variables if needed
# ENV MY_VAR=value

# Expose ports if your app uses any (uncomment and set correct port)
# EXPOSE 8080

# Default command when the container starts
CMD ["/bin/bash"]
