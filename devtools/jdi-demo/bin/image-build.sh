
cd ..
mvn clean
mvn -DskipTests=true install

#mvn dockerfile:build

docker build --build-arg JAR_FILE=target/jdi-demo-0.0.1-SNAPSHOT.jar -f Dockerfile -t huiwq1990/jdi-demo .
