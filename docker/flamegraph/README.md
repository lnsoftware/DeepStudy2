


docker build -f Dockerfile -t huiwq1990/flamegraph  .



docker run -i -t --privileged -v `pwd`/workspace:/root/workspace huiwq1990/flamegraph /bin/bash


cmake安装
http://blog.csdn.net/jmlikun/article/details/49930955