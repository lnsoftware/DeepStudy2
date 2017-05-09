package com.alibaba.dubbo.common.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class proxyIns0
    implements ClassGenerator.DC, ProxyTest.ITest
{
    public static Method[] methods;
    private InvocationHandler handler;

    public proxyIns0(InvocationHandler paramInvocationHandler)
    {
        this.handler = paramInvocationHandler;
    }

    public proxyIns0() {}

    public String getName()
    {
        Object[] arrayOfObject = new Object[0];
        Object localObject = this.handler.invoke(this, methods[0], arrayOfObject);
        return (String)localObject;
    }

    public void setName(String paramString1, String paramString2)
    {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString2;
        Object localObject = this.handler.invoke(this, methods[1], arrayOfObject);
    }
}
