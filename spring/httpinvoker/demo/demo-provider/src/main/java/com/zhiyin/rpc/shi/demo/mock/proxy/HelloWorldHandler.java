package com.zhiyin.rpc.shi.demo.mock.proxy;

 import com.alibaba.fastjson.JSON;
 import com.google.common.collect.Lists;
 import lombok.extern.slf4j.Slf4j;

 import java.lang.reflect.InvocationHandler;
 import java.lang.reflect.Method;
 import java.util.List;

/**
 * 实现在方法调用前后向控制台输出两句字符串
 * 
 * @author jiqinlin
 *
 */
 @Slf4j
 public class HelloWorldHandler implements InvocationHandler{
    //要代理的原始对象
     private Object obj;
    
    public HelloWorldHandler(Object obj) {
        super();
        this.obj = obj;
    }

    /**
     * 在代理实例上处理方法调用并返回结果
     * 
     * @param proxy 代理类
     * @param method 被代理的方法
     * @param args 该方法的参数数组
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        //调用之前
//         doBefore();


        //调用原始对象的方法
        try {
            result = method.invoke(obj, args);
            return result;
        }catch (Exception e){
//            log.error("call method error.",e);
        }

        Object mockResult = null;

        log.info("call method name:" + method.getName());

        Class<?>[] parameterTypes = null;
        if( args != null && args.length > 0 ) {
            // 参数类型
            parameterTypes = new Class[args.length];

            for (int i = 0; i < args.length; i++) {
//                log.info("arg class name: " +arg.getClass().getName());
                parameterTypes[i] = args[i].getClass();
            }
        }

            Class<?>[] interfaces = obj.getClass().getInterfaces();
            for (Class<?> anInterface : interfaces) {
                Method interfaceMethod = anInterface.getDeclaredMethod(method.getName(), parameterTypes);
                if(interfaceMethod == null){
                    continue;
                }

                String mockClassName = anInterface.getName() + "Mock";

//                log.info("Mock Class Name: " + mockClassName);
                Class<?> mockClass = Class.forName(mockClassName);
                Object mockInstance = mockClass.newInstance();

                mockResult = method.invoke(mockInstance, args);
//                log.info(JSON.toJSONString(mockResult));
            }


        //调用之后
//        doAfter();
        return mockResult;
    }
    
    private void doBefore(){
        System.out.println("before method invoke");
    }
    
    private void doAfter(){
        System.out.println("after method invoke");
    }
    
}