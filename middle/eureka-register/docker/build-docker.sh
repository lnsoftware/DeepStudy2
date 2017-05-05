
curPath=`pwd`

cd ..

mvn clean package -DskipTests -pl eureka-server

cd $curPath

cp ../eureka-server/target/eureka-server*.jar ./eureka-server.jar
docker build --file=Dockerfile.eureka.server --tag=eureka-server:latest --rm=true .
rm -rf ./eureka-server.jar





