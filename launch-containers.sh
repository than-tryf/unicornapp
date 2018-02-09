#!/usr/bin/env bash

clear

# produce the dummy java app
mvn clean install

# Very rough initialization --> need to make this part 'smarter'

##################################################
# kill and delete ALL running containers - USE WITH CAUTION
docker stop $(docker ps -a -q)

docker rm $(docker ps -a -q)
docker rmi $(docker images -q)
##################################################

# Build Envoy Base Container --> prduces unicornbase/envoy-enabled container
docker build -t unicornbase/envoy-enabled -f $PWD/containerization/unicornbase/Dockerfile-Envoy $PWD

# Build Java Base Container which contains Oracle Java 8 & Envoy --> unicornbase/java-enabled
docker build -t unicornbase/java-enabled -f /home/thanasis/IdeaProjects/unicornapp/containerization/unicornbase/Dockerfile-UnicornJava $PWD

# Build the Java app container within the Unicorn Javabased container
docker build -t unicornbase/dummyapp -f /home/thanasis/IdeaProjects/unicornapp/containerization/dummyapp/Dockerfile-DummyApp $PWD



# Build a simple network for the container to run on

docker network rm unicornmesh

docker network create -d bridge unicornmesh


# Run the container


docker run -d --memory="4g" --cpus="2" --network=unicornmesh --net-alias=dummyapp --name=dummyapp unicornbase/dummyapp

clear

# Visual confirmation of the running container
docker ps
