package com.zhiyin.rpc.shi.demo.mock.proxy;

 /**
 * 定义一个HelloWorld接口
 * 
 * @author jiqinlin
 *
 */
 public class HelloWorldMock implements HelloWorld {
     public void sayHelloWorld() {
         System.out.println("HelloWorld!");
     }

     @Override
     public String hello(String arg) {
         return "mock hello "+arg;
     }

     @Override
     public String hello(String arg, Integer throwExp) {
         return "mock throw exp, hello "+arg;
     }
 }