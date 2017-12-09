#!/bin/bash

cd ${ROCKETMQ_HOME}/bin

export JAVA_OPT=" -Duser.home=${BASE_DIR}"

export NAMESRV_ADDR=localhost:9876

echo 'start mqnamesrv'
nohup sh mqnamesrv &
echo 'mqnamesrv end'

sleep 2

echo 'mqbroker start'
nohup sh mqbroker  -n localhost:9876 -c broker.properties &
echo 'mqbroker end'


java -jar rocketmq-console-ng-1.0.0.jar