#!/bin/bash
set -e

echo "### Starting UBK Application Deployment ###"

# Step 1: Build the application JAR
echo "--> Building Spring Boot application..."
./mvnw clean package -DskipTests

# Step 2: Set up environment variables
# Create a .env file if it doesn't exist
if [ ! -f .env ]; then
  echo "--> .env file not found. Creating a template. Please fill it out."
  cat > .env << EOL
# PostgreSQL
DB_USER=user
DB_PASSWORD=supersecretpassword
DB_NAME=ubk

# Redis (no auth configured)

# JWT
JWT_SECRET=aVeryStrongAndLongSecretKeyForProductionEnvironmentThatYouShouldChange

# Tunduk Keystore/Truststore (example paths)
# TUNDUK_KEYSTORE_PATH=file:/path/to/keystore.p12
# TUNDUK_KEYSTORE_PASSWORD=keystore_pass
# TUNDUK_TRUSTSTORE_PATH=file:/path/to/truststore.p12
# TUNDUK_TRUSTSTORE_PASSWORD=truststore_pass
EOL
  echo "--> .env file created. Please edit it with your production values and re-run."
  exit 1
fi

# Step 3: Start the services using Docker Compose
echo "--> Starting services with Docker Compose (production profile)..."
docker-compose -f docker-compose.prod.yml up --build -d

echo "### UBK Application Deployment Complete! ###"
echo "Application should be running at http://localhost:8080"
