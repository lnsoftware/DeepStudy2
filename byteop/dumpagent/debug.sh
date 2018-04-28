

#java -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:63533,suspend=y,server=n

cd target

# set the main class
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -classpath jagent-demo-0.0.1-SNAPSHOT-jar-with-all-dependencies.jar com.zhiyin.jagent.test.runner.DemoServer

#java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -classpath "/Users/hg/Github/DeepStudy2/byteop/jagent-demo/target/classes:/Users/hg/.m2/repository/org/projectlombok/lombok/1.16.8/lombok-1.16.8.jar:/Users/hg/.m2/repository/org/slf4j/slf4j-api/1.7.8/slf4j-api-1.7.8.jar:/Users/hg/.m2/repository/ch/qos/logback/logback-core/1.1.2/logback-core-1.1.2.jar:/Users/hg/.m2/repository/ch/qos/logback/logback-classic/1.1.2/logback-classic-1.1.2.jar:/Users/hg/.m2/repository/com/google/guava/guava/19.0/guava-19.0.jar:/Users/hg/Github/DeepStudy2/byteop/jagent-demo/target/classes" com.zhiyin.jagent.test.runner.DemoServer
