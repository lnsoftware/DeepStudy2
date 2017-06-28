/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.zhiyin.cglib.cando.DemoService
 *  net.sf.cglib.core.Signature
 *  net.sf.cglib.reflect.FastClass
 */
package com.zhiyin.cglib.cando;

import com.zhiyin.cglib.cando.DemoService;
import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.core.Signature;
import net.sf.cglib.reflect.FastClass;

public class DemoService$$FastClassByCGLIB$$3215ecb4
    extends FastClass {
    public DemoService$$FastClassByCGLIB$$3215ecb4(Class class_) {
        super(class_);
    }

    public int getIndex(Signature signature) {
        String string = signature.toString();
        switch (string.hashCode()) {
            case 848333779: {
                if (!string.equals("hello(Ljava/lang/String;)Ljava/lang/String;"))
                    break;
                return 0;
            }
            case 1826985398: {
                if (!string.equals("equals(Ljava/lang/Object;)Z"))
                    break;
                return 1;
            }
            case 1913648695: {
                if (!string.equals("toString()Ljava/lang/String;"))
                    break;
                return 2;
            }
            case 1984935277: {
                if (!string.equals("hashCode()I"))
                    break;
                return 3;
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
        DemoService demoService = (DemoService) object;
        try {
            switch (n) {
                case 0: {
                    return demoService.hello((String) arrobject[0]);
                }
                case 1: {
                    return new Boolean(demoService.equals(arrobject[0]));
                }
                case 2: {
                    return demoService.toString();
                }
                case 3: {
                    return new Integer(demoService.hashCode());
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
                    return new DemoService();
                }
            }
        } catch (Throwable throwable) {
            throw new InvocationTargetException(throwable);
        }
        throw new IllegalArgumentException("Cannot find matching method/constructor");
    }

    public int getMaxIndex() {
        return 3;
    }
}
