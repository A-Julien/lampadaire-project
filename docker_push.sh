#!/bin/bash

#build docker tag
PARAM="$@"
TAG=${PROJECT_VERSION}_${PARAM}

#export docker tag env var for xml
export TAG

#login to dockerhub
echo "${DOCKER_PASSWORD}" | docker login --username "${DOCKER_USERNAME}" --password-stdin

#build&push image
./mvnw package -X -Pprod verify -DskipTests jib:build
