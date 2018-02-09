
wget https://raw.githubusercontent.com/wiki/alibaba/jvm-sandbox/release/sandbox-stable-bin.zip




### jetty

1. jetty服务器的初始化

com.alibaba.jvm.sandbox.core.server.jetty.JettyCoreServer#initJettyContextHandler
初始化servlet，ModuleHttpServlet，它会根据Http注解解析路径

2、设置contextpath

对于http的处理路径前缀为： ip:port//sandbox/module/http


查看沙箱信息
curl localhost:34023/sandbox/module/http/info/version
version命令实现 com.albaba.jvm.sandbox.module.mgr.InfoModule