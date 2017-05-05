
curPath=`pwd`

cd ..

mvn clean package -DskipTests -pl config-server,config-client,spring-cloud-config-server-mysql

cd $curPath
cp ../config-server/target/config-server*.jar ./config-server.jar

docker build --file=Dockerfile.config.server --tag=config-server:latest --rm=true .
