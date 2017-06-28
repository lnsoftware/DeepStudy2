package com.zhiyin.cglib.cando;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestInterfaceMaker {

    public static void main(
        String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        //抽取某个类的方法生成接口方法  
        interfaceMaker.add(TargetObject.class);
        Class<?> targetInterface = interfaceMaker.create();
        for (Method method : targetInterface.getMethods()) {
            System.out.println(method.getName());
        }
        //接口代理并设置代理接口方法拦截  
        Object object = Enhancer.create(Object.class, new Class[] {targetInterface}, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("method1")) {
                    System.out.println("filter method1 ");
                    return "mmmmmmmmm";
                }
                if (method.getName().equals("method2")) {
                    System.out.println("filter method2 ");
                    return 1111111;
                }
                if (method.getName().equals("method3")) {
                    System.out.println("filter method3 ");
                    return 3333;
                }
                return "default";
            }
        });
        Method targetMethod1 = object.getClass().getMethod("method3", new Class[] {int.class});
        int i = (int) targetMethod1.invoke(object, new Object[] {33});
        Method targetMethod = object.getClass().getMethod("method1", new Class[] {String.class});
        System.out.println(targetMethod.invoke(object, new Object[] {"sdfs"}));
    }

}  