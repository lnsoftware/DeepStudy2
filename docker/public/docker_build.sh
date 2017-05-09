#!/bin/bash 

modelName=tomcat
docker build --file=Dockerfile.${modelName} --tag=huiwq1990/${modelName}:latest --rm=true .
