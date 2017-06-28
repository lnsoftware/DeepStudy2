package jdk;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import sun.misc.ProxyGenerator;

interface IHelloService {
    public String hello(String name);
}

/**
 * JDK代理，导出代理类代码。
 * Created by hg on 2017/6/28.
 */
public class JdkProxyGenFileTest {

    @Test
    public void genFile() throws IOException {
        //被动态代理的
        IHelloService helloA = new HelloService();
        //生成对象A的动态代理
        IHelloService helloAProxy = (IHelloService) JavaDynamicProxy.generateProxy(helloA);
        helloAProxy.hello("HG");

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", HelloService.class.getInterfaces());
        FileUtils.writeByteArrayToFile(new File("./target/DumpProxy.class"), classFile);
    }
}

class HelloService implements IHelloService {
    public String hello(String name) {
        return "hello" + name;
    }
}

class JavaDynamicProxy implements InvocationHandler {

    /**
     * 被代理者
     */
    private Object inner;

    private JavaDynamicProxy(Object inner) {
        this.inner = inner;
    }

    /**
     * 生成代理类
     */
    public static Object generateProxy(Object inner) {
        return Proxy.newProxyInstance(
            inner.getClass().getClassLoader(),
            inner.getClass().getInterfaces(),
            new JavaDynamicProxy(inner));
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before.");
        Object result = method.invoke(inner, args);

        System.out.println("end.");
        return result;
    }
}