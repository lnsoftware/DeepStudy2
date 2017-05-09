# /bin/bash

jarPath=./target/eureka-peer-0.0.1-SNAPSHOT.jar

java -jar ${jarPath} --spring.profiles.active=peer1
java -jar ${jarPath} --spring.profiles.active=peer2