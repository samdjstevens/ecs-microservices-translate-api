#!/usr/bin/env bash
./gradlew clean build && docker build -t samdjstevens/ecs-microservices-translate-api:latest .