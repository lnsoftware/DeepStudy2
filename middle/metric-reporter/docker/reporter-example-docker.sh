# build
curPath=`pwd`

cd ..

cd elasticsearch-metrics-reporter-java
mvn clean install -DskipTests

cd ..

modelName=reporter-example

mvn clean install -DskipTests

docker ps -aq -f status=exited | xargs docker rm