#!/bin/bash
#build image
./mvnw package -Pprod verify jib:dockerBuild

#build docker tag
PARAM="$@"
TAG=${PROJECT_VERSION}_${PARAM}

#push image
cd ${HOME} || return
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker images
docker tag lampaderum $DOCKER_USERNAME/lampaderum:${TAG}
docker push $DOCKER_USERNAME/lampaderum:${TAG}
