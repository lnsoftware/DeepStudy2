
FROM huiwq1990/openjdk8:centos7

RUN yum install -y wget git
RUN yum install -y gcc gcc-c++
RUN yum install -y make automake cmake
RUN yum install -y perf 


#RUN wget https://cmake.org/files/v3.3/cmake-3.3.2.tar.gz && tar xzvf cmake-3.3.2.tar.gz
#RUN cd cmake-3.3.2 && ./bootstrap && gmake && gmake install

#RUN wget http://www.cmake.org/files/v2.8/cmake-2.8.10.2.tar.gz && tar -zxvf cmake-2.8.10.2.tar.gz
#RUN cd cmake-2.8.10.2 && ./bootstrap && gmake && gmake install

#RUN wget http://www.cmake.org/files/v2.8/cmake-2.8.12.tar.gz && tar -zxvf cmake-2.8.12.tar.gz
#RUN cd cmake-2.8.12 && ./bootstrap && gmake && gmake install

RUN mkdir -p /usr/lib/jvm 
WORKDIR /usr/lib/jvm
RUN git clone https://github.com/jvm-profiling-tools/perf-map-agent 
RUN cd perf-map-agent/ && cmake . && make

WORKDIR /opt
RUN git clone https://github.com/brendangregg/FlameGraph.git

ENV JAVA_HOME /etc/alternatives/java_sdk
ENV USER root
ENV PATH="/opt/FlameGraph:${PATH}" 

RUN mkdir -p /root/workspace
WORKDIR /root/workspace
ADD DemoServer.java DemoServer.java 
RUN javac DemoServer.java 
#java DemoServer &


