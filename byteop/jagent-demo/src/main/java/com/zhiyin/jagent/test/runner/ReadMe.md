

## 配置Maven
需要引入 tool.jar

配置 MANNIFEST.MF

Agent-Class: com.hg.awesome.java.agent.premain.GetAllLoadedClassAgent

运行 RunServer

在命令行使用 jps -l 查看程序的ID

设置RunAgent的processID，运行RunAgent

可以在RunServer的输出路径里看到 相应输出。




java -Djava.ext.dirs=lib\ -Djava.library.path="%JAVA_HOME%"\jre\bin -cp webdemo-0.0.1-SNAPSHOT.jar com.hg.awesome.java.agent.premain.RunAgent

java -cp webdemo-0.0.1-SNAPSHOT.jar  com.hg.awesome.java.agent.premain.RunServer