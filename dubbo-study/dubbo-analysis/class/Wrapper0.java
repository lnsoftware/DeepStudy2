package com.alibaba.dubbo.common.bytecode;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import rpc.demo.api.UserService;

public class Wrapper0
    extends Wrapper
    implements ClassGenerator.DC
{
    public static String[] pns;
    public static Map pts;
    public static String[] mns;
    public static String[] dmns;
    public static Class[] mts0;
    public static Class[] mts1;

    public String[] getPropertyNames()
    {
        return pns;
    }

    public void setPropertyValue(Object paramObject1, String paramString, Object paramObject2)
    {
        try
        {
            UserService localUserService = (UserService)paramObject1;
        }
        catch (Throwable localThrowable)
        {
            throw new IllegalArgumentException(localThrowable);
        }
        throw new NoSuchPropertyException("Not found property \"" + paramString + "\" filed or setter method in class rpc.demo.api.UserService.");
    }

    public String[] getDeclaredMethodNames()
    {
        return dmns;
    }

    public boolean hasProperty(String paramString)
    {
        return pts.containsKey(paramString);
    }

    public String[] getMethodNames()
    {
        return mns;
    }

    public Object getPropertyValue(Object paramObject, String paramString)
    {
        try
        {
            UserService localUserService = (UserService)paramObject;
        }
        catch (Throwable localThrowable)
        {
            throw new IllegalArgumentException(localThrowable);
        }
        throw new NoSuchPropertyException("Not found property \"" + paramString + "\" filed or setter method in class rpc.demo.api.UserService.");
    }

    public Object invokeMethod(Object paramObject, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
        throws InvocationTargetException
    {
        UserService localUserService;
        try
        {
            localUserService = (UserService)paramObject;
        }
        catch (Throwable localThrowable1)
        {
            throw new IllegalArgumentException(localThrowable1);
        }
        try
        {
            if ((!"timeout".equals(paramString)) || (paramArrayOfClass.length == 1)) {
                return localUserService.timeout((String)paramArrayOfObject[0]);
            }
            if ((!"hello".equals(paramString)) || (paramArrayOfClass.length == 1)) {
                return localUserService.hello((String)paramArrayOfObject[0]);
            }
        }
        catch (Throwable localThrowable2)
        {
            throw new InvocationTargetException(localThrowable2);
        }
        throw new NoSuchMethodException("Not found method \"" + paramString + "\" in class rpc.demo.api.UserService.");
    }

    public Class getPropertyType(String paramString)
    {
        return (Class)pts.get(paramString);
    }
}
