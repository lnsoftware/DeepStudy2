#!/bin/bash 
docker rm -f rocketmq
docker run -d -p 10911:10911 -p 10909:10909 -p 9876:9876 --name rocketmq rocketmq
