


modelName=grafana
docker build --file=Dockerfile.${modelName} --tag=${modelName}:latest --rm=true .


#   docker run -i -p 3000:3000 grafana


docker run -e "TZ=Asia/shanghai" -v /etc/localtime:/etc/localtime:ro -i -p 3000:3000 grafana





docker rm $(docker ps -q -f status=exited)

docker ps -aq -f status=exited | xargs docker rm


