

### io.netty.bootstrap.Bootstrap.doConnect

```java
  private ChannelFuture doConnect(final SocketAddress remoteAddress, final SocketAddress localAddress) {
        final ChannelFuture regFuture = initAndRegister();
        final Channel channel = regFuture.channel();
        if (regFuture.cause() != null) {
            return regFuture;
        }

        final ChannelPromise promise = channel.newPromise();
        if (regFuture.isDone()) {
            doConnect0(regFuture, channel, remoteAddress, localAddress, promise);
        } else {
            regFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    doConnect0(regFuture, channel, remoteAddress, localAddress, promise);
                }
            });
        }

        return promise;
    }
```

regFuture是一个Future类，仔细观察if这段代码，可能会有疑惑。如果执行```regFuture.isDone()```时，结果为false；当调用addListener时，regFuture经执行完成。那Listener怎么再触发运行？？

### addListener怎么运行

io.netty.util.concurrent.DefaultPromise.addListener
```java
 synchronized (this) {
    addListener0(listener);
}

if (isDone()) {
    notifyListeners();
}
```

可以看到，添加完Listener后，会继续判断任务是否完成，如果完成触发Listener执行。这样可以避免添加Listener时，future执行完成。

### 执行成功

io.netty.util.concurrent.DefaultPromise.trySuccess
io.netty.util.concurrent.DefaultPromise.setSuccess

```java
    public boolean trySuccess(V result) {
        if (setSuccess0(result)) {
            notifyListeners();
            return true;
        }
        return false;
    }
```

当future执行成功时，需要显示调用trySuccess或者setSuccess，它们会执行notifyListeners通知Listener执行。

