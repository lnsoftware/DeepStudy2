//package com.hg.dubbo.study.proxy;
//
//import com.alibaba.dubbo.common.bytecode.ClassGenerator;
//import com.alibaba.dubbo.rpc.service.EchoService;
//import com.hg.dubbo.study.javassist.DemoService;
//
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
///**
// * Created by hg on 2017/4/23.
// */
//
//public class proxy1
//        implements ClassGenerator.DC, EchoService, DemoService
//{
//    public static Method[] methods;
//    private InvocationHandler handler;
//    public String sayHello(String paramString)
//    {
//        Object[] arrayOfObject = new Object[1];
//        arrayOfObject[0] = paramString;
//        Object localObject =
//                null;
//        try {
//            localObject = this.handler.invoke(
//                    this, methods[0],
//                    arrayOfObject);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return ((String)localObject);
//    }
//    public Object $echo(Object paramObject)
//    {
//        Object[] arrayOfObject = new Object[1];
//        arrayOfObject[0] = paramObject;
//
//        Object localObject =
//                null;
//        try {
//            localObject = this.handler.invoke(
//                    this, methods[1],
//                    arrayOfObject);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return ((Object)localObject);
//    }
//    public proxy1(InvocationHandler paramInvocationHandler)
//    {
//        this.handler =
//                paramInvocationHandler;
//    }
//}