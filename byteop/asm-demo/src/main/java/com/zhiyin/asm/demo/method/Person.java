package com.zhiyin.asm.demo.method;

import java.util.concurrent.TimeUnit;

public class Person {
    public String name = "zhangzhuo";

    public int timer;
  
    public void sayHello() {  
        System.out.println("Hello World!");  
    }  
  
    public void sleep() {  
        try {  
            System.out.println("我要睡一会...");  
            TimeUnit.SECONDS.sleep(2);//沉睡两秒
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  