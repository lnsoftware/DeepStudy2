

docker ps -aq -f status=exited | xargs docker rm

docker run --name=eureka-server --publish=8761:8761 eureka-server:latest
