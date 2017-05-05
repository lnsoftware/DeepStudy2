# build
curPath=`pwd`

cd ..

modelName=hystrix-metrics-record

mvn clean package -DskipTests -pl ${modelName}

cd $curPath

cp ../${modelName}/target/${modelName}*.jar ./${modelName}.jar
docker build --file=Dockerfile.hystrix-metrics-record --tag=eureka-server:latest --rm=true .
rm -rf ./eureka-server.jar





