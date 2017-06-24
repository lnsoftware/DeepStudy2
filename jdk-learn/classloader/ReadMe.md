
http://qiongsong.com/java/2015/03/20/classloader/

http://www.blogjava.net/heavensay/archive/2012/11/07/389685.html


 Java由于其晚绑定和“解释型”的特性，类型的加载是到最晚才进行，一个类型直到被调用构造函数、静态方法或者在字段上使用时才会被加载。
 
 
 
 
 
 类加载发生在以下几种情况： 
 1）new生成新的对象实例。 
 2）使用java.lang.reflect包的方法对类进行发射调用时。 
 3）当子类进行加载或初始化时。当加载一个类时，如果发现其存在父类并且未被加载则会继续加载父类。 
 4）虚拟机启动时，用户指定的执行主类（包含main()的执行入口类），虚拟机会加载加载该类。 
 5）调用类的变量（静态字段但非静态常量），类方法
 
 注意： 
 1. 调用类的静态字段，只有直接定义这个字段的类才会被加载和初始化。通过其子类来引用父类中定义的字段，只会触发父类的初始化而不会触发子类的初始化。 
 2.调用类的静态常量是不触发类的加载过程。如果在A类中调用B类的静态常量，那么在编译阶段会将该静态常量放到A的Class文件的静态常量池中，所以对该常量的调用不涉及B的加载。
 
 
 
 类加载实现
 
 ### 自定义类加载器
 
 自定义类加载器会调用父类的构造函数
 
 ```java
    public LocalPathClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }
```

ClassLoader会设置好parent，getSystemClassLoader是父Classloader
```java
    protected ClassLoader() {
        this(checkCreateClassLoader(), getSystemClassLoader());
    }
```
ParallelLoaders代表类加载器是否支持并发加载

```java
    private ClassLoader(Void unused, ClassLoader parent) {
        this.parent = parent;
        if (ParallelLoaders.isRegistered(this.getClass())) {
            parallelLockMap = new ConcurrentHashMap<>();
            package2certs = new ConcurrentHashMap<>();
            domains =
                Collections.synchronizedSet(new HashSet<ProtectionDomain>());
            assertionLock = new Object();
        } else {
            // no finer-grained lock; lock on the classloader instance
            parallelLockMap = null;
            package2certs = new Hashtable<>();
            domains = new HashSet<>();
            assertionLock = this;
        }
    }
```
 
 
### 参考

 http://blog.jobbole.com/96145/
 http://ifeve.com/classloader/
 http://blog.csdn.net/briblue/article/details/54973413