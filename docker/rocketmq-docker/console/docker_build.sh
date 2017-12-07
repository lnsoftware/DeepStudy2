#!/bin/bash 

#docker build -t mqconsole .


cd /Users/hg/Gitopen/incubator-rocketmq-externals/rocketmq-console

mvn clean package -Dmaven.test.skip=true docker:build

