/**
 * 被动使用类字段演示三：
 *
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，
 * 因此不会触发定义常量的类的初始化。

 因为在编译的时候，常量（static final 修饰的）会存入调用类的常量池【这里说的是main函数所在的类的常量池】，调用的时候本质上没有引用到定义常量的类，而是直接访问了自己的常量池。

 使用javap -c -verbose 查看


 **/

class SuperClass{
    static{
        System.out.println("SuperClass 类初始化");
    }
    static int a=3;
    static final int b=4;
}

class SubClass2 extends SuperClass{
    static{
        System.out.println("SubClass 类初始化");
    }
}

public class StaticFinalNotInitClass {

    public static final int a = 1;

    public static void main(String[] args) {
        System.out.println(SubClass2.a);
        System.out.println(SubClass2.b);
        System.out.println(a);
    }
}

