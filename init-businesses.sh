#!/bin/bash
set -e

echo "Waiting for business-service to be available..."

until curl -s -o /dev/null http://business-service:8080/actuator/health; do
  echo "Waiting for business-service..."
  sleep 2
done

echo "Business-service is up. Initializing data..."

curl -X POST http://business-service:8080/v1/businesses \
  -H "Content-Type: application/json" \
  -d '{
        "id": "6c485d83-4071-40a3-af25-cdfe8c5b7f1f",
        "address": {
          "street": "456 Market Street",
          "city": "San Francisco",
          "state": "CA",
          "country": "USA"
        },
        "location": {
          "latitude": 37.7749,
          "longitude": -122.4194
        }
      }'

echo "Business initialized successfully 🚀"