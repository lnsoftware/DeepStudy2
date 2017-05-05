# build
curPath=`pwd`

cd ..

modelName=docker-timezone

#mvn clean package -DskipTests

mvn clean package -DskipTests -pl ${modelName}

cd $curPath

cp ../${modelName}/target/${modelName}*.jar ./${modelName}.jar
docker build --file=Dockerfile.${modelName} --tag=${modelName}:latest --rm=true .
rm -rf ./${modelName}.jar

docker run -i -t ${modelName}






