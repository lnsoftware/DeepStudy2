
Java动态代理的机制是利用反射机制生成。例如代理接口是IHello，代理类会实现这个接口，并且实现方法通过调用InvocationHandler进行调用。

```java
    public final void hello() throws  {
        try {
            super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }
```

Java动态代理的限制

(1).被代理的类要求至少实现了一个Interface;
(2).被代理的类要求有public的构造函数(即没有显示的将其设置为private等);
(3).被代理的类要求不是final;


导出代理类的方法

```java
	byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", HelloImpl.class.getInterfaces());
	FileUtils.writeByteArrayToFile(new File("Demo.class"),classFile);
```

鉴于Java动态代理的限制，我们有需要代理没有任何实现接口的类的时候，可以考虑使用CGLib。CGLib的全称是Code Generate Library。