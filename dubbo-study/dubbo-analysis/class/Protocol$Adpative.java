package com.alibaba.dubbo.rpc;

import com.alibaba.dubbo.common.Node;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class Protocol$Adpative
    implements Protocol
{
    public void destroy()
    {
        throw new UnsupportedOperationException("method public abstract void com.alibaba.dubbo.rpc.Protocol.destroy() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!");
    }

    public int getDefaultPort()
    {
        throw new UnsupportedOperationException("method public abstract int com.alibaba.dubbo.rpc.Protocol.getDefaultPort() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!");
    }

    public Exporter export(Invoker paramInvoker)
        throws RpcException
    {
        if (paramInvoker == null) {
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
        }
        if (paramInvoker.getUrl() == null) {
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");
        }
        URL localURL = paramInvoker.getUrl();
        String str = localURL.getProtocol() == null ? "dubbo" : localURL.getProtocol();
        if (str == null) {
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + localURL.toString() + ") use keys([protocol])");
        }
        Protocol localProtocol = (Protocol)ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(str);
        return localProtocol.export(paramInvoker);
    }

    public Invoker refer(Class paramClass, URL paramURL)
        throws RpcException
    {
        if (paramURL == null) {
            throw new IllegalArgumentException("url == null");
        }
        URL localURL = paramURL;
        String str = localURL.getProtocol() == null ? "dubbo" : localURL.getProtocol();
        if (str == null) {
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + localURL.toString() + ") use keys([protocol])");
        }
        Protocol localProtocol = (Protocol)ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(str);
        return localProtocol.refer(paramClass, paramURL);
    }
}
