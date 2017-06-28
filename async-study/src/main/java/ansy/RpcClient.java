package ansy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RpcClient implements InvocationHandler {
    TestInterface testInterface;
    private long timeoutMills = 0;
    private RpcInvokeHook rpcInvokeHook = null;
    private String host;
    private int port;

    public RpcClient(long timeoutMills, RpcInvokeHook rpcInvokeHook, String host, int port) {
        this.timeoutMills = timeoutMills;
        this.rpcInvokeHook = rpcInvokeHook;
        this.host = host;
        this.port = port;
    }

    public RpcFuture call(String methodName, Object... args) {
        if (rpcInvokeHook != null)
            rpcInvokeHook.beforeInvoke(methodName, args);

        System.out.print("invoke method = " + methodName + " args =");
        for (Object argObject : args) {
            System.out.print(" " + argObject.toString());
        }
        System.out.println("");

        //simulation for remote invoke
        RpcFuture rpcFuture = new RpcFuture();
        TestThread testThread = new TestThread(rpcFuture, methodName, args);
        testThread.start();

        return rpcFuture;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        RpcFuture rpcFuture = call(method.getName(), args);
        Object result;
        if (timeoutMills == 0)
            result = rpcFuture.get();
        else
            result = rpcFuture.get(timeoutMills);

        if (rpcInvokeHook != null)
            rpcInvokeHook.afterInvoke(method.getName(), args);

        return result;
    }

    public void connect() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("connect to " + host + ":" + port + " success!");

        testInterface = new TestInterface() {
            public String testMethod01(String string) {
                return string.toUpperCase();
            }
        };
    }

    //simulation for remote invoke
    //delay 2000ms and return the result
    class TestThread extends Thread {
        String methodName;
        Object[] args;
        RpcFuture rpcFuture;

        public TestThread(RpcFuture rpcFuture, String methodName, Object[] args) {
            this.rpcFuture = rpcFuture;
            this.methodName = methodName;
            this.args = args;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                int parameterCount = args.length;
                Method method;
                if (parameterCount > 0) {
                    Class<?>[] parameterTypes = new Class[args.length];
                    for (int i = 0; i < parameterCount; i++) {
                        parameterTypes[i] = args[i].getClass();
                    }
                    method = testInterface.getClass().getMethod(methodName, parameterTypes);
                } else {
                    method = testInterface.getClass().getMethod(methodName);
                }

                rpcFuture.setResult(method.invoke(testInterface, args));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}