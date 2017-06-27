package com.zhiyin.proxy.cg;

/**
 * 模拟fastclass原理
 */
public class CglibFastClassTest {
    public static void main(String[] args){
        Test tt = new Test();
        TestFastClass fc = new TestFastClass();
        int index = fc.getIndex("f()V");
        fc.invoke(index, tt, null);
    }
}

class Test{
    public void f(){
        System.out.println("f method");
    }
    
    public void g(){
        System.out.println("g method");
    }
}
class TestFastClass{
    public Object invoke(int index, Object o, Object[] ol){
        Test t = (Test) o;
        switch(index){
        case 1:
            t.f();
            return null;
        case 2:
            t.g();
            return null;
        }
        return null;
    }
    
    public int getIndex(String signature){
        switch(signature.hashCode()){
        case 3078479:
            return 1;
        case 3108270:
            return 2;
        }
        return -1;
    }
}