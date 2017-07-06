
### channel

server端channelClass为NioServerSocketChannel

```
    public B channel(Class<? extends C> channelClass) {
        if (channelClass == null) {
            throw new NullPointerException("channelClass");
        }
        return channelFactory(new BootstrapChannelFactory<C>(channelClass));
    }
```