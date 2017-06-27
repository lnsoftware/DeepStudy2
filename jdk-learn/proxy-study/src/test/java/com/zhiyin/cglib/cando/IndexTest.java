//package com.zhiyin.cglib.cando;
//
//import java.lang.reflect.Method;
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
///**
// * Created by hg on 2017/6/26.
// */
//public class IndexTest {
//    public static void main(String[] args) {
//        User o = new SlaveProxy().getInstance(User.class);
//        IndexName indexName=new IndexName();
//        indexName.setName("no name");
//        indexName.setType("liuyibo");
//        o.setIndexName(indexName);
//        System.out.println(o.getIndexName());
//    }
//}
//
//
//class SlaveProxy implements MethodInterceptor {
//    public <T> T getInstance(Class<T> cls) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(cls);
//        enhancer.setCallback(this);
//        T o = (T) enhancer.create();
//        return o;
//    }
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        Object returnObject = methodProxy.invokeSuper(o, objects);
//        return returnObject;
//    }
//}