#!/usr/bin/env bash

REGISTRY=put-registry-here
IMAGE=$REGISTRY/ecs-microservices-translate-api
TAG=latest

# compile the app
./gradlew clean build && \

# split the .jar into layers for smaller app layer size
mkdir -p build/dependency && (cd build/dependency && jar -xf ../libs/*.jar) && \

# build the dockerfile and tag
docker build -t $IMAGE:$TAG . && \

# push to ECR
aws ecr get-login-password --region eu-west-2 | docker login --username AWS --password-stdin $REGISTRY && \
docker push $IMAGE:$TAG