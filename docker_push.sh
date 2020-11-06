#!/bin/bash
cd ${HOME} || return
echo "${DOCKER_PASSWORD}" | docker login --username "${DOCKER_USERNAME}" --password-stdin

PARAM="$@"
TAG=${PROJECT_VERSION}_${PARAM}

#build docker tag
docker build -f src/main/docker/lampaderum-prod-springboot.dockerfile -t lampaderum_back .
docker build -f src/main/docker/lampaderum-prod-nginx-angular.dockerfile -t lampaderum_frontend .

docker images

docker tag lampaderum_back $DOCKER_USERNAME/lampaderum_back:${TAG}
docker tag lampaderum_frontend $DOCKER_USERNAME/lampaderum_frontend:${TAG}

#push image
docker push $DOCKER_USERNAME/lampaderum_back:${TAG}
docker push $DOCKER_USERNAME/lampaderum_frontend:${TAG}
