类初始化的时机，有且仅有四个：

1、遇到new、getstatic、putstatic、invokestatic这四条字节码指令的时候。

2、使用Java.lang.reflect进行反射调用的时候。

3、当初始化一个类的时候，发现其父类还没有初始化，那么先去初始化它的父类。

4、当虚拟机启动的时候，需要初始化main函数所在的类。





 因为在编译的时候，常量（static final 修饰的）会存入调用类的常量池【这里说的是main函数所在的类的常量池】，调用的时候本质上没有引用到定义常量的类，而是直接访问了自己的常量池。


```
  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: getstatic     #3                  // Field SubClass2.a:I
         6: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
         9: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        12: iconst_4
        13: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
        16: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        19: iconst_1
        20: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
        23: return
      LineNumberTable:
```


https://segmentfault.com/a/1190000004527951




### 静态块、初始化块、构造函数原理

```
class C extends B {

    static {
        System.out.println("Static init C.");
    }

    {
        System.out.println("Instance init C.");
    }

    C() {
        System.out.println("Constructor C.");
    }
}
```

```
class C extends B {
  C();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method B."<init>":()V
       4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       7: ldc           #3                  // String Instance init C.
       9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      12: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      15: ldc           #5                  // String Constructor C.
      17: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      20: return

  static {};
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #6                  // String Static init C.
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```

静态初始化块仍然单独分出一部分，输出了我们的调试语句。而另一部分，仍然还是类C的构造函数C();，可以看到它先调用了父类B的构造函数，接着输出了我们初始化块中的语句，然后才输出我们写在构造函数中的语句，最后返回。多次试验也都是如此。于是我们能够推断：初始化块的代码是被加入到子类构造函数的前面，父类初始化的后面了。



http://www.cnblogs.com/BlackStorm/p/5699965.html