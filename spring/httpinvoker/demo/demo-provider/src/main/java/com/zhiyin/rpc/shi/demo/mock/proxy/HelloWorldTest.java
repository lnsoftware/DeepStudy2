package com.zhiyin.rpc.shi.demo.mock.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class HelloWorldTest {

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorldImpl();
        InvocationHandler handler = new HelloWorldHandler(helloWorld);
        
        //创建动态代理对象
        HelloWorld proxy=(HelloWorld)Proxy.newProxyInstance(
                helloWorld.getClass().getClassLoader(), 
                helloWorld.getClass().getInterfaces(), 
                handler);
        proxy.sayHelloWorld();
        String ret = proxy.hello("admin");
        System.out.println(ret);

        ret = proxy.hello("admin",1);
        System.out.println(ret);
    }
}