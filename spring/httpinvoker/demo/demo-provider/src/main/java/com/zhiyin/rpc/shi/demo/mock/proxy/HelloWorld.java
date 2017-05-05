package com.zhiyin.rpc.shi.demo.mock.proxy;

 /**
 * 定义一个HelloWorld接口
 * 
 * @author jiqinlin
 *
 */
 public interface HelloWorld {
    public void sayHelloWorld();
     public String hello(String arg);

     String hello(String arg, Integer throwExp);
 }