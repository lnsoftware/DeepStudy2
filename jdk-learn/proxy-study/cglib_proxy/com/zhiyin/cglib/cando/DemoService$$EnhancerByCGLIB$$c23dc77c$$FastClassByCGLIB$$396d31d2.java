/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.sf.cglib.core.Signature
 *  net.sf.cglib.proxy.Callback
 *  net.sf.cglib.reflect.FastClass
 */
package com.zhiyin.cglib.cando;

import com.zhiyin.cglib.cando.DemoService$$EnhancerByCGLIB$$c23dc77c;
import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.reflect.FastClass;

public class DemoService$$EnhancerByCGLIB$$c23dc77c$$FastClassByCGLIB$$396d31d2
    extends FastClass {
    public DemoService$$EnhancerByCGLIB$$c23dc77c$$FastClassByCGLIB$$396d31d2(Class class_) {
        super(class_);
    }

    public int getIndex(Signature signature) {
        String string = signature.toString();
        switch (string.hashCode()) {
            case -2055565910: {
                if (!string.equals("CGLIB$SET_THREAD_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V"))
                    break;
                return 18;
            }
            case -1882565338: {
                if (!string.equals("CGLIB$equals$1(Ljava/lang/Object;)Z"))
                    break;
                return 13;
            }
            case -1457535688: {
                if (!string.equals("CGLIB$STATICHOOK1()V"))
                    break;
                return 11;
            }
            case -1411842725: {
                if (!string.equals("CGLIB$hashCode$3()I"))
                    break;
                return 15;
            }
            case -894172689: {
                if (!string.equals("newInstance(Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;"))
                    break;
                return 6;
            }
            case -879968516: {
                if (!string.equals("CGLIB$hello$0(Ljava/lang/String;)Ljava/lang/String;"))
                    break;
                return 12;
            }
            case -623122092: {
                if (!string.equals("CGLIB$findMethodProxy(Lnet/sf/cglib/core/Signature;)Lnet/sf/cglib/proxy/MethodProxy;"))
                    break;
                return 9;
            }
            case -508378822: {
                if (!string.equals("clone()Ljava/lang/Object;"))
                    break;
                return 3;
            }
            case -419626537: {
                if (!string.equals("setCallbacks([Lnet/sf/cglib/proxy/Callback;)V"))
                    break;
                return 10;
            }
            case 560567118: {
                if (!string.equals("setCallback(ILnet/sf/cglib/proxy/Callback;)V"))
                    break;
                return 8;
            }
            case 811063227: {
                if (!string.equals("newInstance([Ljava/lang/Class;[Ljava/lang/Object;[Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;"))
                    break;
                return 5;
            }
            case 848333779: {
                if (!string.equals("hello(Ljava/lang/String;)Ljava/lang/String;"))
                    break;
                return 7;
            }
            case 973717575: {
                if (!string.equals("getCallbacks()[Lnet/sf/cglib/proxy/Callback;"))
                    break;
                return 20;
            }
            case 1221173700: {
                if (!string.equals("newInstance([Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;"))
                    break;
                return 4;
            }
            case 1230699260: {
                if (!string.equals("getCallback(I)Lnet/sf/cglib/proxy/Callback;"))
                    break;
                return 19;
            }
            case 1306468936: {
                if (!string.equals("CGLIB$toString$2()Ljava/lang/String;"))
                    break;
                return 14;
            }
            case 1584330438: {
                if (!string.equals("CGLIB$SET_STATIC_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V"))
                    break;
                return 17;
            }
            case 1800494055: {
                if (!string.equals("CGLIB$clone$4()Ljava/lang/Object;"))
                    break;
                return 16;
            }
            case 1826985398: {
                if (!string.equals("equals(Ljava/lang/Object;)Z"))
                    break;
                return 0;
            }
            case 1913648695: {
                if (!string.equals("toString()Ljava/lang/String;"))
                    break;
                return 1;
            }
            case 1984935277: {
                if (!string.equals("hashCode()I"))
                    break;
                return 2;
            }
        }
        return -1;
    }

    /*
     * Exception decompiling
     */
    public int getIndex(String var1_1, Class[] var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:423)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
        // org.benf.cfr.reader.Main.doClass(Main.java:46)
        // org.benf.cfr.reader.Main.main(Main.java:191)
        throw new IllegalStateException("Decompilation failed");
    }

    public int getIndex(Class[] arrclass) {
        switch (arrclass.length) {
            case 0: {
                return 0;
            }
        }
        return -1;
    }

    public Object invoke(int n, Object object, Object[] arrobject) throws InvocationTargetException {
        DemoService$$EnhancerByCGLIB$$c23dc77c demoService$$EnhancerByCGLIB$$c23dc77c = (DemoService$$EnhancerByCGLIB$$c23dc77c) ((Object) object);
        try {
            switch (n) {
                case 0: {
                    return new Boolean(demoService$$EnhancerByCGLIB$$c23dc77c.equals(arrobject[0]));
                }
                case 1: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.toString();
                }
                case 2: {
                    return new Integer(demoService$$EnhancerByCGLIB$$c23dc77c.hashCode());
                }
                case 3: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.clone();
                }
                case 4: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.newInstance((Callback[]) arrobject[0]);
                }
                case 5: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.newInstance((Class[]) arrobject[0], (Object[]) arrobject[1], (Callback[]) arrobject[2]);
                }
                case 6: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.newInstance((Callback) arrobject[0]);
                }
                case 7: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.hello((String) arrobject[0]);
                }
                case 8: {
                    demoService$$EnhancerByCGLIB$$c23dc77c.setCallback(((Number) arrobject[0]).intValue(), (Callback) arrobject[1]);
                    return null;
                }
                case 9: {
                    return DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$findMethodProxy((Signature) arrobject[0]);
                }
                case 10: {
                    demoService$$EnhancerByCGLIB$$c23dc77c.setCallbacks((Callback[]) arrobject[0]);
                    return null;
                }
                case 11: {
                    DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$STATICHOOK1();
                    return null;
                }
                case 12: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$hello$0((String) arrobject[0]);
                }
                case 13: {
                    return new Boolean(demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$equals$1(arrobject[0]));
                }
                case 14: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$toString$2();
                }
                case 15: {
                    return new Integer(demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$hashCode$3());
                }
                case 16: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$clone$4();
                }
                case 17: {
                    DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_STATIC_CALLBACKS((Callback[]) arrobject[0]);
                    return null;
                }
                case 18: {
                    DemoService$$EnhancerByCGLIB$$c23dc77c.CGLIB$SET_THREAD_CALLBACKS((Callback[]) arrobject[0]);
                    return null;
                }
                case 19: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.getCallback(((Number) arrobject[0]).intValue());
                }
                case 20: {
                    return demoService$$EnhancerByCGLIB$$c23dc77c.getCallbacks();
                }
            }
        } catch (Throwable throwable) {
            throw new InvocationTargetException(throwable);
        }
        throw new IllegalArgumentException("Cannot find matching method/constructor");
    }

    public Object newInstance(int n, Object[] arrobject) throws InvocationTargetException {
        try {
            switch (n) {
                case 0: {
                    return new DemoService$$EnhancerByCGLIB$$c23dc77c();
                }
            }
        } catch (Throwable throwable) {
            throw new InvocationTargetException(throwable);
        }
        throw new IllegalArgumentException("Cannot find matching method/constructor");
    }

    public int getMaxIndex() {
        return 20;
    }
}
