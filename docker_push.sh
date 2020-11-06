#!/bin/bash

#build docker tag
PARAM="$@"
TAG=${PROJECT_VERSION}_${PARAM}

#export docker tag env var for xml
export TAG

#push image
./mvnw package -X -Pprod verify -DskipTests jib:build
