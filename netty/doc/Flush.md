Netty的写操作由两个步骤组成：
1、Write:将msg存储到ChannelOutboundBuffer中
2、Flush：将msg从ChannelOutboundBuffer中flush到套接字的发送缓冲区中。


io.netty.channel.AbstractChannelHandlerContext.writeAndFlush

io.netty.channel.AbstractChannelHandlerContext.invokeWriteAndFlush

io.netty.channel.AbstractChannelHandlerContext.invokeFlush0

```java
 private void invokeFlush0() {
        try {
            ((ChannelOutboundHandler) handler()).flush(this);
        } catch (Throwable t) {
            notifyHandlerException(t);
        }
    }
```
flush是Outbound事件，findContextOutbound()会找到pipeline的head节点，触发flush方法。

io.netty.channel.DefaultChannelPipeline.HeadContext.handler



io.netty.channel.AbstractChannel.AbstractUnsafe.flush

io.netty.channel.AbstractChannel.AbstractUnsafe.flush0
1、如果当前socketChannel已经关闭，或断开连接，则执行失败操作。
2、否则执行doWrite把数据写入到socketChannel。

io.netty.channel.socket.nio.NioSocketChannel.doWrite