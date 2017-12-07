
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
RUN chmod +x bin/*.sh
RUN chmod +x bin/mqnamesrv

CMD cd ${ROCKETMQ_HOME}/bin && export JAVA_OPT=" -Duser.home=${BASE_DIR}" && sh mqnamesrv

EXPOSE 9876
VOLUME /opt/logs \
		/opt/store

