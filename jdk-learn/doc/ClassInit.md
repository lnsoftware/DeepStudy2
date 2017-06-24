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