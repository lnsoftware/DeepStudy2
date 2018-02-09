
cd ..
mvn run

mvn exec:java -Dexec.mainClass="org.springframework.boot.devtools.RemoteSpringApplication" -Dexec.args="arg0 arg1 arg2"
