/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.sf.cglib.proxy.Callback
 *  net.sf.cglib.proxy.Factory
 *  net.sf.cglib.proxy.MethodInterceptor
 */
package com.zhiyin.cglib.cando;

import com.zhiyin.cglib.cando.DemoService$$EnhancerByCGLIB$$c23dc77c;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;

public class DemoService$$EnhancerByCGLIB$$5ad554c1
    implements Factory {
    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static final Callback[] CGLIB$STATIC_CALLBACKS;
    public static Object CGLIB$FACTORY_DATA;
    private boolean CGLIB$BOUND;
    private MethodInterceptor CGLIB$CALLBACK_0;

    public DemoService$$EnhancerByCGLIB$$5ad554c1() {
        DemoService$$EnhancerByCGLIB$$5ad554c1 demoService$$EnhancerByCGLIB$$5ad554c1 = this;
        DemoService$$EnhancerByCGLIB$$5ad554c1 demoService$$EnhancerByCGLIB$$5ad554c12 = demoService$$EnhancerByCGLIB$$5ad554c1;
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] arrcallback) {
        CGLIB$THREAD_CALLBACKS.set(arrcallback);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] arrcallback) {
        CGLIB$STATIC_CALLBACKS = arrcallback;
    }

    private static final void CGLIB$BIND_CALLBACKS(Object object) {
        DemoService$$EnhancerByCGLIB$$5ad554c1 demoService$$EnhancerByCGLIB$$5ad554c1 = (DemoService$$EnhancerByCGLIB$$5ad554c1) object;
        if (!demoService$$EnhancerByCGLIB$$5ad554c1.CGLIB$BOUND) {
            demoService$$EnhancerByCGLIB$$5ad554c1.CGLIB$BOUND = true;
            Object t = CGLIB$THREAD_CALLBACKS.get();
            if (t != null || (v17 = CGLIB$STATIC_CALLBACKS) != null) {
                demoService$$EnhancerByCGLIB$$5ad554c1.CGLIB$CALLBACK_0 = (MethodInterceptor) ((Callback[]) t)[0];
            }
        }
    }

    public Object newInstance(Callback[] arrcallback) {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(arrcallback);
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(null);
        return new DemoService$$EnhancerByCGLIB$$c23dc77c();
    }

    public Object newInstance(Callback callback) {
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(new Callback[] {callback});
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
                **break;
            }
        }
        throw new IllegalArgumentException("Constructor not found");
        lbl6:
        // 1 sources:
        DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS(null);
        return new DemoService$$EnhancerByCGLIB$$c23dc77c();
    }

    public Callback getCallback(int n) {
        MethodInterceptor methodInterceptor;
        DemoService$$EnhancerByCGLIB$$5ad554c1.CGLIB$BIND_CALLBACKS(this);
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
                this.CGLIB$CALLBACK_0 = (MethodInterceptor) callback;
                break;
            }
        }
    }

    public Callback[] getCallbacks() {
        DemoService$$EnhancerByCGLIB$$5ad554c1.CGLIB$BIND_CALLBACKS(this);
        DemoService$$EnhancerByCGLIB$$5ad554c1 demoService$$EnhancerByCGLIB$$5ad554c1 = this;
        return new Callback[] {this.CGLIB$CALLBACK_0};
    }

    public void setCallbacks(Callback[] arrcallback) {
        Callback[] arrcallback2 = arrcallback;
        Callback[] arrcallback3 = arrcallback2;
        DemoService$$EnhancerByCGLIB$$5ad554c1 demoService$$EnhancerByCGLIB$$5ad554c1 = this;
        this.CGLIB$CALLBACK_0 = (MethodInterceptor) arrcallback2[0];
    }
}
