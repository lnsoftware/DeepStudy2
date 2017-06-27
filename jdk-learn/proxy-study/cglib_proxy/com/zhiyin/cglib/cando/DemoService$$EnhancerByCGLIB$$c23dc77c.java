/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.zhiyin.cglib.cando.DemoService
 *  net.sf.cglib.core.ReflectUtils
 *  net.sf.cglib.core.Signature
 *  net.sf.cglib.proxy.Callback
 *  net.sf.cglib.proxy.Factory
 *  net.sf.cglib.proxy.MethodInterceptor
 *  net.sf.cglib.proxy.MethodProxy
 */
package com.zhiyin.cglib.cando;

import com.zhiyin.cglib.cando.DemoService;
import java.lang.reflect.Method;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DemoService$$EnhancerByCGLIB$$c23dc77c
extends DemoService
implements Factory {
    private boolean CGLIB$BOUND;
    public static Object CGLIB$FACTORY_DATA;
    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static final Callback[] CGLIB$STATIC_CALLBACKS;
    private MethodInterceptor CGLIB$CALLBACK_0;
    private static final Method CGLIB$hello$0$Method;
    private static final MethodProxy CGLIB$hello$0$Proxy;
    private static final Object[] CGLIB$emptyArgs;
    private static final Method CGLIB$equals$1$Method;
    private static final MethodProxy CGLIB$equals$1$Proxy;
    private static final Method CGLIB$toString$2$Method;
    private static final MethodProxy CGLIB$toString$2$Proxy;
    private static final Method CGLIB$hashCode$3$Method;
    private static final MethodProxy CGLIB$hashCode$3$Proxy;
    private static final Method CGLIB$clone$4$Method;
    private static final MethodProxy CGLIB$clone$4$Proxy;

    static void CGLIB$STATICHOOK1() {
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class class_ = Class.forName("com.zhiyin.cglib.cando.DemoService$$EnhancerByCGLIB$$c23dc77c");
        Class class_2 = Class.forName("java.lang.Object");
        Method[] arrmethod = ReflectUtils.findMethods((String[])new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, (Method[])class_2.getDeclaredMethods());
        CGLIB$equals$1$Method = arrmethod[0];
        CGLIB$equals$1$Proxy = MethodProxy.create(class_2, class_, (String)"(Ljava/lang/Object;)Z", (String)"equals", (String)"CGLIB$equals$1");
        CGLIB$toString$2$Method = arrmethod[1];
        CGLIB$toString$2$Proxy = MethodProxy.create(class_2, class_, (String)"()Ljava/lang/String;", (String)"toString", (String)"CGLIB$toString$2");
        CGLIB$hashCode$3$Method = arrmethod[2];
        CGLIB$hashCode$3$Proxy = MethodProxy.create(class_2, class_, (String)"()I", (String)"hashCode", (String)"CGLIB$hashCode$3");
        CGLIB$clone$4$Method = arrmethod[3];
        CGLIB$clone$4$Proxy = MethodProxy.create(class_2, class_, (String)"()Ljava/lang/Object;", (String)"clone", (String)"CGLIB$clone$4");
        class_2 = Class.forName("com.zhiyin.cglib.cando.DemoService");
        CGLIB$hello$0$Method = ReflectUtils.findMethods((String[])new String[]{"hello", "(Ljava/lang/String;)Ljava/lang/String;"}, (Method[])class_2.getDeclaredMethods())[0];
        CGLIB$hello$0$Proxy = MethodProxy.create(class_2, class_, (String)"(Ljava/lang/String;)Ljava/lang/String;", (String)"hello", (String)"CGLIB$hello$0");
    }

    final String CGLIB$hello$0(String string) {
        return super.hello(string);
    }

    public final String hello(String string) {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            return (String)methodInterceptor.intercept((Object)this, CGLIB$hello$0$Method, new Object[]{string}, CGLIB$hello$0$Proxy);
        }
        return super.hello(string);
    }

    final boolean CGLIB$equals$1(Object object) {
        return super.equals(object);
    }

    public final boolean equals(Object object) {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            Object object2 = methodInterceptor.intercept((Object)this, CGLIB$equals$1$Method, new Object[]{object}, CGLIB$equals$1$Proxy);
            return object2 == null ? false : (Boolean)object2;
        }
        return super.equals(object);
    }

    final String CGLIB$toString$2() {
        return super.toString();
    }

    public final String toString() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            return (String)methodInterceptor.intercept((Object)this, CGLIB$toString$2$Method, CGLIB$emptyArgs, CGLIB$toString$2$Proxy);
        }
        return super.toString();
    }

    final int CGLIB$hashCode$3() {
        return super.hashCode();
    }

    public final int hashCode() {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            Object object = methodInterceptor.intercept((Object)this, CGLIB$hashCode$3$Method, CGLIB$emptyArgs, CGLIB$hashCode$3$Proxy);
            return object == null ? 0 : ((Number)object).intValue();
        }
        return super.hashCode();
    }

    final Object CGLIB$clone$4() throws CloneNotSupportedException {
        return super.clone();
    }

    protected final Object clone() throws CloneNotSupportedException {
        MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
        if (methodInterceptor == null) {
            DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
            methodInterceptor = this.CGLIB$CALLBACK_0;
        }
        if (methodInterceptor != null) {
            return methodInterceptor.intercept((Object)this, CGLIB$clone$4$Method, CGLIB$emptyArgs, CGLIB$clone$4$Proxy);
        }
        return super.clone();
    }

    public static MethodProxy CGLIB$findMethodProxy(Signature signature) {
        String string = signature.toString();
        switch (string.hashCode()) {
            case -508378822: {
                if (!string.equals("clone()Ljava/lang/Object;")) break;
                return CGLIB$clone$4$Proxy;
            }
            case 848333779: {
                if (!string.equals("hello(Ljava/lang/String;)Ljava/lang/String;")) break;
                return CGLIB$hello$0$Proxy;
            }
            case 1826985398: {
                if (!string.equals("equals(Ljava/lang/Object;)Z")) break;
                return CGLIB$equals$1$Proxy;
            }
            case 1913648695: {
                if (!string.equals("toString()Ljava/lang/String;")) break;
                return CGLIB$toString$2$Proxy;
            }
            case 1984935277: {
                if (!string.equals("hashCode()I")) break;
                return CGLIB$hashCode$3$Proxy;
            }
        }
        return null;
    }

    public DemoService$$EnhancerByCGLIB$$c23dc77c() {
        DemoService$$EnhancerByCGLIB$$c23dc77c demoService$$EnhancerByCGLIB$$c23dc77c = this;
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)demoService$$EnhancerByCGLIB$$c23dc77c);
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] arrcallback) {
        CGLIB$THREAD_CALLBACKS.set(arrcallback);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] arrcallback) {
        CGLIB$STATIC_CALLBACKS = arrcallback;
    }

    private static final void CGLIB$BIND_CALLBACKS(Object object) {
        DemoService$$EnhancerByCGLIB$$c23dc77c demoService$$EnhancerByCGLIB$$c23dc77c = (DemoService$$EnhancerByCGLIB$$c23dc77c)((Object)object);
        if (!demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BOUND) {
            demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BOUND = true;
            Object t = CGLIB$THREAD_CALLBACKS.get();
            if (t != null || (v282 = CGLIB$STATIC_CALLBACKS) != null) {
                demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$CALLBACK_0 = (MethodInterceptor)((Callback[])t)[0];
            }
        }
    }

    public Object newInstance(Callback[] arrcallback) {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(arrcallback);
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(null);
        return new DemoService$$EnhancerByCGLIB$$c23dc77c();
    }

    public Object newInstance(Callback callback) {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(new Callback[]{callback});
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(null);
        return new DemoService$$EnhancerByCGLIB$$c23dc77c();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public Object newInstance(Class[] var1_1, Object[] var2_2, Callback[] var3_3) {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(var3_3);
        switch (var1_1.length) {
            case 0: {
                ** break;
            }
        }
        throw new IllegalArgumentException("Constructor not found");
lbl6: // 1 sources:
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(null);
        return new DemoService$$EnhancerByCGLIB$$c23dc77c();
    }

    public Callback getCallback(int n) {
        MethodInterceptor methodInterceptor;
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
        switch (n) {
            case 0: {
                methodInterceptor = this.CGLIB$CALLBACK_0;
                break;
            }
            default: {
                methodInterceptor = null;
            }
        }
        return methodInterceptor;
    }

    public void setCallback(int n, Callback callback) {
        switch (n) {
            case 0: {
                this.CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
                break;
            }
        }
    }

    public Callback[] getCallbacks() {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$BIND_CALLBACKS((Object)this);
        DemoService$$EnhancerByCGLIB$$c23dc77c demoService$$EnhancerByCGLIB$$c23dc77c = this;
        return new Callback[]{this.CGLIB$CALLBACK_0};
    }

    public void setCallbacks(Callback[] arrcallback) {
        Callback[] arrcallback2 = arrcallback;
        Callback[] arrcallback3 = arrcallback2;
        DemoService$$EnhancerByCGLIB$$c23dc77c demoService$$EnhancerByCGLIB$$c23dc77c = this;
        this.CGLIB$CALLBACK_0 = (MethodInterceptor)arrcallback2[0];
    }

    static {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$STATICHOOK1();
    }
}
