
docker build -f Dockerfile -t huiwq1990/kernel-build  .

docker run -i -t -v `pwd`:/root huiwq1990/kernel-build /bin/bash

docker run -v "$(pwd):/root" huiwq1990/kernel-build make


docker run --privileged -v "$(pwd):/root" huiwq1990/kernel-build make
