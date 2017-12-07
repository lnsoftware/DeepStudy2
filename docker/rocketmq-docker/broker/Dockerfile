FROM java:8

# Rocketmq version
ENV ROCKETMQ_VERSION 4.0.0-incubating

ENV BASE_DIR /opt
# Rocketmq home
ENV ROCKETMQ_HOME  ${BASE_DIR}/rocketmq-${ROCKETMQ_VERSION}

WORKDIR  ${ROCKETMQ_HOME}

RUN mkdir -p \
		${BASE_DIR}/logs \
		${BASE_DIR}/store
RUN curl http://mirror.bit.edu.cn/apache/rocketmq/${ROCKETMQ_VERSION}/rocketmq-all-${ROCKETMQ_VERSION}-bin-release.zip -o rocketmq.zip \
          && unzip rocketmq.zip \
          && mv apache-rocketmq-all/* . \
          && rmdir apache-rocketmq-all  \
          && rm rocketmq.zip


ADD *.sh ${ROCKETMQ_HOME}/bin/
RUN chmod +x bin/mqbroker
RUN chmod +x bin/*.sh


ADD broker.properties ${ROCKETMQ_HOME}/bin/

# 配置nameserver的地址
CMD cd ${ROCKETMQ_HOME}/bin && export JAVA_OPT=" -Duser.home=${BASE_DIR}" && echo ${JAVA_OPT} && sh mqbroker -n mqnamesrv:9876 -c broker.properties
EXPOSE 10909 10911
VOLUME /opt/logs \
		/opt/store

