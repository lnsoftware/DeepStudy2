package com.zhiyin.rpc.shi.demo.mock.proxy;

 /**
 * 类HelloWorldImpl是HelloWorld接口的实现
 * 
 * @author jiqinlin
 *
 */
 public class HelloWorldImpl implements HelloWorld{

    public void sayHelloWorld() {
        System.out.println("HelloWorld!");
    }

     @Override
     public String hello(String arg) {
         return "hello "+arg;
     }


     @Override
     public String hello(String arg,Integer throwExp) {
         if(throwExp == 1){
             throw new RuntimeException("");
         }
         return "hello "+arg;
     }



 }