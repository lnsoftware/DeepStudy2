
cd ..
mvn install > /dev/null

cd target/classes

className=EscapeAnalysisTest

echo '逃逸分析开启：'
java -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC ${className}
echo '逃逸分析关闭：'
java -server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC ${className}

time java -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis ${className}

time java -server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis ${className}
