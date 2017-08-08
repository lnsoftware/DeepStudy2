

docker run


docker run -v "$(pwd):/root" ubuntu:16:04  /bin/bash


docker run -i -t -v `pwd`:/root ubuntu:16.04 /bin/bash


docker build -f Dockerfile -t huiwq1990/linux-study .