# build

modelName=elasticsearch
docker build --file=Dockerfile.${modelName} --tag=huiwq1990/${modelName}:2.4.0 --rm=true .
