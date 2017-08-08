



```java
public static SelectorProvider provider() {  
        synchronized (lock) {  
            if (provider != null)  
                return provider;  
            return AccessController.doPrivileged(  
                new PrivilegedAction<SelectorProvider>() {  
                    public SelectorProvider run() {  
                            if (loadProviderFromProperty())  
                                return provider;  
                            if (loadProviderAsService())  
                                return provider;  
                            provider = sun.nio.ch.DefaultSelectorProvider.create();  
                            return provider;  
                        }  
                    });  
        }  
} 
```

其中provider = sun.nio.ch.DefaultSelectorProvider.create();会根据操作系统来返回不同的实现类。

mac:KQueueSelectorProvider
win:WindowsSelectorProvider
linux:EPollSelectorProvider


```java
//构造函数
KQueueSelectorImpl(SelectorProvider var1) {
    //调用父类的构造函数，传入的是一个SelectorProvider，在Mac上是KQueueSelectorProvider
    super(var1);
    long var2 = IOUtil.makePipe(false);//native方法
    this.fd0 = (int)(var2 >>> 32);//高32位存放的是Pipe管道的读端的文件描述符
    this.fd1 = (int)var2;//低32位存放的是Pipe管道的写端的文件描述符
    this.kqueueWrapper = new KQueueArrayWrapper();
    this.kqueueWrapper.initInterrupt(this.fd0, this.fd1);
    this.fdMap = new HashMap();
    this.totalChannels = 1;
}
```





EPollSelectorImpl实现
https://yq.aliyun.com/articles/58917