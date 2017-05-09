

dubbo的扩展机制和java的SPI机制非常相似，但是又增加了如下功能：

1 可以方便的获取某一个想要的扩展实现，java的SPI机制就没有提供这样的功能

2 对于扩展实现IOC依赖注入功能：

举例来说：接口A，实现者A1、A2。接口B，实现者B1、B2。

现在实现者A1含有setB()方法，会自动注入一个接口B的实现者，此时注入B1还是B2呢？都不是，而是注入一个动态生成的接口B的实现者B$Adpative，该实现者能够根据参数的不同，自动引用B1或者B2来完成相应的功能

3 对扩展采用装饰器模式进行功能增强，类似AOP实现的功能

还是以上面的例子，接口A的另一个实现者AWrapper1。大体内容如下：

```
private A a; 
AWrapper1（A a）{

   this.a=a;
}
```

因此，我们在获取某一个接口A的实现者A1的时候，已经自动被AWrapper1包装了。






@SPI 注解：可以认为是定义默认实现类；
比如 Protocol 接口中，定义默认协议时 dubbo；
@SPI("dubbo")
public interface Protocol {}

@Adaptive 注解
该注解打在接口方法上；调 ExtensionLoader.getAdaptiveExtension()获
取设配类，会先通过前面的过程生成 java 的源代码，在通过编译器编译成 class 加载。
但是 Compiler 的实现策略选择也是通过 ExtensionLoader.getAdaptiveExtension()，如果也
通过编译器编译成 class 文件那岂不是要死循环下去了吗？
此时分析 ExtensionLoader.getAdaptiveExtension()函数，对于有实现类上去打了注解
@Adaptive 的 dubbo spi 扩展机制，它获取设配类不在通过前面过程生成设配类 java 源代码，
而是在读取扩展文件的时候遇到实现类打了注解@Adaptive 就把这个类作为设配类缓存在
ExtensionLoader 中，调用是直接返回。
3） filter 和 listener
在生成具体的实现类对象时，不是直接读取类文件，而是在读取类文件的基础
上，通过 filter 和 listener 去封装类对象；