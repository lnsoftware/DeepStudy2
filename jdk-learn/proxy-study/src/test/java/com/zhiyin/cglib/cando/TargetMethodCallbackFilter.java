package com.zhiyin.cglib.cando;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

/**
 * 回调方法过滤
 *
 * @author zghw
 */
public class TargetMethodCallbackFilter implements CallbackFilter {

    /**
     * 过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
     */
    @Override
    public int accept(Method method) {
        if (method.getName().equals("method1")) {
            System.out.println("filter method1 ==0");
            return 0;
        }
        if (method.getName().equals("method2")) {
            System.out.println("filter method2 ==1");
            return 1;
        }
        if (method.getName().equals("method3")) {
            System.out.println("filter method3 ==2");
            return 2;
        }
        // 默认为0
        return 0;
    }

    public static void main(String args[]) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FilterClass.class);
        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();

        /**
         (1)callback1：方法拦截器
         (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
         (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback noopCb = NoOp.INSTANCE;

        Callback callback1 = new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before: " + method);
                Object ret = proxy.invokeSuper(obj, args);
                System.out.println("after: " + method);
                return ret;
            }
        };

        Callback fixedValue = new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return 1;
            }
        };
        Callback[] cbarray = new Callback[] {callback1, noopCb, fixedValue};

        enhancer.setCallbacks(cbarray);
        enhancer.setCallbackFilter(callbackFilter);
        FilterClass proxy = (FilterClass) enhancer.create();
        System.out.println(proxy);

        System.out.println( proxy.method3(1));

    }

}

class FilterClass {

    public String method1(String paramName) {
        return paramName;
    }

    public int method2(int count) {
        return count;
    }

    public int method3(int count) {
        return count;
    }

}
