# build
curPath=`pwd`

cd ..

modelName=reporter-example

#mvn clean package -DskipTests

mvn clean package -DskipTests -pl ${modelName}

cd $curPath

cp ../${modelName}/target/${modelName}*.jar ./${modelName}.jar
docker build --file=Dockerfile.${modelName} --tag=${modelName}:latest --rm=true .
#rm -rf ./${modelName}.jar



#modelName=grafana
#docker build --file=Dockerfile.${modelName} --tag=${modelName}:latest --rm=true .






