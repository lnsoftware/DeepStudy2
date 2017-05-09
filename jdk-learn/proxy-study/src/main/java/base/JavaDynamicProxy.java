package base;

import br.com.fabriciodeb.sample.IHello;
import br.com.fabriciodeb.sample.impl.HelloImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaDynamicProxy implements InvocationHandler {

    /**
     * 被代理者
     */
    private Object inner;

    /**
     * 生成代理类
     */
    public static Object generateProxy(Object inner) {
        return Proxy.newProxyInstance(
            inner.getClass().getClassLoader(),
            inner.getClass().getInterfaces(),
            new JavaDynamicProxy(inner));
    }

    private JavaDynamicProxy(Object inner) {
        this.inner = inner;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        doSomethingBefore();

        Object result = method.invoke(inner, args);

        doSomethingAfter();

        return result;
    }

    private void doSomethingBefore() {
        System.out.println("JavaDynamicProxy: Before...");
    }

    private void doSomethingAfter() {
        System.out.println("JavaDynamicProxy: After...");
    }

    //使用
    public static void main(String[] args) {

        System.out.println("-------------------------------------------------");
        // 一个JavaDynamicProxy可以一直使用

        //被动态代理的IHello实例对象B
//        IHello helloB = new HelloWithLogImpl();
//        //生成对象B的动态代理
//        IHello helloBProxy = (IHello) JavaDynamicProxy.generateProxy(helloB);
//        helloBProxy.hello();
//
//        System.out.println("-------------------------------------------------");
//        //被动态代理对象IBye实例
//        IBye bye = new ByeImpl();
//        //生成IBye实例的动态代理
//        IBye byeProxy = (IBye) JavaDynamicProxy.generateProxy(bye);
//        byeProxy.bye();
    }
}