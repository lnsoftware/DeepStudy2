

https://hub.docker.com/r/library/redis/



docker run --name some-redis -d redis

docker run -it --link some-redis:redis --rm redis redis-cli -h redis -p 6379
