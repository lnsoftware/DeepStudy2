
FROM ubuntu:14.04

RUN apt-get update && apt-get install -y software-properties-common && apt-get install -y gcc && apt-get install -y gdb && apt-get install -y vim
# 安装JDK
RUN apt-get install -y software-properties-common && \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer 

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# 安装必要软件
RUN add-apt-repository universe
RUN apt-get -q -y install ant python-dev libboost-dev libboost-test-dev libboost-program-options-dev libevent-dev \ 
    automake libtool flex bison pkg-config g++ libssl-dev sed make gcc gdb

RUN mkdir /opt/thrift && cd /opt/thrift \
    && wget http://archive.apache.org/dist/thrift/0.8.0/thrift-0.8.0.tar.gz \
    && tar xvzf thrift-0.8.0.tar.gz \
    && cd thrift-0.8.0 \
# 不要cpp功能
    && ./configure  --without-ruby --without-python --without-perl --without-php --without-cpp

RUN  cd /opt/thrift/thrift-0.8.0  && make -j 2 \
      && make install \
      && ldconfig \
      && cd / && rm -fr /opt/thrift \
      && apt-get -q -y clean && rm -rf /var/cache/apt/archives/* /var/lib/apt/lists/* && rm -fr /tmp/*


# hack Thrift
#RUN sed -i '/#ifdef HAVE_NETINET_IN_H/ i\#define HAVE_NETINET_IN_H 1' /usr/local/include/thrift/Thrift.h \
#    && sed -i '/#ifdef HAVE_INTTYPES_H/ i\#define HAVE_INTTYPES_H 1' /usr/local/include/thrift/Thrift.h

CMD ["/usr/local/bin/thrift", "--help"]    
