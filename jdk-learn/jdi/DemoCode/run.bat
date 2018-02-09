cd TEST/src
javac -classpath "%JAVA_HOME%\lib\tools.jar;." Trace.java
javac -g MainThread.java
java -classpath "%JAVA_HOME%\lib\tools.jar;." Trace  -output "C:\tf.log" MainThread
pause