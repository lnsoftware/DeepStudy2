


Dubbo 2.0.5版本以后支持telnet命令，可以用来debug各个dubbo接口。

Dubbo官网：http://dubbo.io/User+Guide-zh.htm

telnet涉及的命令包括：

```
ls
ps
cd
pwd
trace
count
invoke
status
log
help
clear
exit
```


1.连接服务

测试对应IP和端口下的dubbo服务是否连通，cmd命令如下

```
telnet localhost 20880
```

正常情况下，进入telnet窗口，键入回车进入dubbo命令模式。

    

2.查看服务列表

ls

显示服务列表。

ls -l

显示服务详细信息列表。

ls XxxService

显示服务的方法列表。

ls -l XxxService

显示服务的方法详细信息列表。


查看服务

```
dubbo>ls

com.test.DemoService
```


查看服务中的接口

```
dubbo>ls com.test.DemoService

queryDemoPageList

insertDemolist

uploadDemoList

deleteDemolist
```


http://xiaobaoqiu.github.io/blog/2015/04/11/telnet-dubbo/

http://www.cnblogs.com/feiqihang/p/4387330.html