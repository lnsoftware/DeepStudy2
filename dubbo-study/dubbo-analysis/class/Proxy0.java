package com.alibaba.dubbo.common.bytecode;

import java.lang.reflect.InvocationHandler;

public class Proxy0
    extends Proxy
    implements ClassGenerator.DC
{
    public Object newInstance(InvocationHandler paramInvocationHandler)
    {
        return new proxyIns0(paramInvocationHandler);
    }
}
