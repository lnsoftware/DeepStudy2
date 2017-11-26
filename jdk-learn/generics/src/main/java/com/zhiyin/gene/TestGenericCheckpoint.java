package com.zhiyin.gene;

public class TestGenericCheckpoint {
    public static void main(String[] args) {  
    //1  
    GenericHolder<String> gh = new GenericHolder<String>();  
    gh.set("sa");//边界 编译器check  
    String sa1 = gh.get();//运行期间仍会checkcast  
    //2  
    SimpleHolder sh = new SimpleHolder();  
    sh.set("sa");//编译器不check任何东西  
    String sa2 = (String) sh.get();//运行期间checkcast  
    }  
}  
  
class GenericHolder<T>{  
    private T object;  
    public void set(T object){  
    this.object = object;  
    }  
    public T get(){  
    return object;  
    }  
}  
  
class SimpleHolder{  
    private Object object;  
    public void set(Object object){  
    this.object = object;  
    }  
    public Object get(){  
    return this.object;  
    }  
}  