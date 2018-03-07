#!/usr/bin/env bash

clear

# produce the dummy java app
mvn clean && mvn package

# Very rough initialization --> need to make this part 'smarter'

##################################################
# kill and delete ALL running containers - USE WITH CAUTION
docker stop $(docker ps -a -q)

docker rm $(docker ps -a -q)
docker rmi $(docker images -q)
##################################################

# Build Load-Balancer
docker build -t unicornapp/lb -f $PWD/containerization/loadbalancer/Dockerfile-lb $PWD


# Build Front End App
docker build -t unicornapp/frontend -f $PWD/containerization/cyflixfrontend/Dockerfile-cyflixfrontend $PWD

# Build a simple network for the container to run on

docker network rm unicornmesh

docker network create -d bridge unicornmesh


# Run the container

docker run -d --memory="4g" --cpus="2"  --network=unicornmesh --net-alias=cyflixfrontend --name=cyflixfrontend unicornapp/frontend

docker run -d --memory="2g" --cpus="2" --network=unicornmesh --net-alias=loadbalancer --name=loadbalancer unicornapp/lb

clear

# Visual confirmation of the running container
docker ps
