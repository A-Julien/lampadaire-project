#!/bin/bash
#build image
./mvnw package -Pprod verify jib:dockerBuild

#build docker tag
PARAM="$@"
TAG=${PROJECT_VERSION}_${PARAM}

#push image
cd ${HOME} || return
echo "${DOCKER_PASSWORD}" | docker login --username "${DOCKER_USERNAME}" --password-stdin
docker images
docker tag travis-ci-build-devops $DOCKER_USERNAME/lampaderum:${TAG}
docker push $DOCKER_USERNAME/travis-ci-build-devops:${TAG}
