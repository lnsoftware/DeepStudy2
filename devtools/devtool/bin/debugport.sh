
cd ../target/classes
java -Djava.security.egd=file:/dev/./urandom -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n Demo

java -Djava.security.egd=file:/dev/./urandom -agentlib:jdwp=transport=dt_socket,server=y,suspend=n Demo
