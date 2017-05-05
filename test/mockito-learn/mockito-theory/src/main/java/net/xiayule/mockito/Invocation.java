package net.xiayule.mockito;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by tan on 16-1-6.
 */
public class Invocation {
    private final Object obj;

    private final Method method;

    private final Object[] arguments;

    private final MethodProxy proxy;

    public Invocation(Object obj, Method method, Object[] args, MethodProxy proxy) {
        this.obj = obj;
        this.method = method;
        this.arguments = copyArgs(args);
        this.proxy = proxy;
    }

    private Object[] copyArgs(Object[] args) {
        Object[] newArgs = new Object[args.length];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        return newArgs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass()))
            return false;

        Invocation other = (Invocation) obj;

        return this.method.equals(other.method) && this.proxy.equals(other.proxy)
                && Arrays.deepEquals(arguments, other.arguments);
    }

    public Object callRealMethod() {
        try {
            return this.proxy.invokeSuper(obj, arguments);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
