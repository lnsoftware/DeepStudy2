

docker ps -aq -f status=exited | xargs docker rm

docker run --name=config-server --publish=8088:8088 config-server:latest

