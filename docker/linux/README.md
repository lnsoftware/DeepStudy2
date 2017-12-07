


docker build -f Dockerfile -t huiwq1990/linux  .


docker run -i -t -v `pwd`:/root huiwq1990/linux /bin/bash

docker run -v "$(pwd):/root" huiwq1990/linux make


docker run --privileged -v "$(pwd):/root" huiwq1990/linux make




## centos

docker build -f Dockerfile.centos7 -t huiwq1990/centos7 .



## ubuntu14

docker build -f Dockerfile.ubuntu14 -t huiwq1990/ubuntu14 .


docker run --privileged -i -t -v "$(pwd):/root" huiwq1990/ubuntu14




## ubuntu16

docker build -f Dockerfile.ubuntu16 -t huiwq1990/ubuntu16 .


docker run --privileged -i -t -v "$(pwd):/root" huiwq1990/ubuntu16


