package com.zhiyin.jagent.transformer;

import com.zhiyin.jagent.scan.ScanHandler;
import com.zhiyin.jagent.transformer.handler.ClassInstrumentationHandler;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class TelemetryTransformer implements ClassFileTransformer {

    private final Set<ClassInstrumentationHandler> handlers = new HashSet<>();

    public void setHandlers( List<String> handlersStr) {

        if(handlersStr == null || handlersStr.size() ==0 ){
            return;
        }
        for (String handler : handlersStr) {
            ClassInstrumentationHandler clasz = ScanHandler.getHandler(handler);
            if(clasz == null){
                continue;
            }
            handlers.add(clasz);
            log.info("add transformer: {}",clasz.getClass().getName());
        }

    }

    public void addHandler(ClassInstrumentationHandler handler) {
        handlers.add(handler);
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        return transform(loader, className, classfileBuffer, ClassPool.getDefault());
    }

    /**
     * Allows specifying the ClassPool, this allows tests to essentially 'reload' classes.
     *
     * @param loader
     * @param className
     * @param classfileBuffer
     * @param cp
     * @return
     * @throws IllegalClassFormatException
     */
    public byte[] transform(ClassLoader loader,
                            String className,
                            byte[] classfileBuffer,
                            ClassPool cp) throws IllegalClassFormatException {
        try {
            final String realClassName = className.replace('/', '.');
            cp.insertClassPath(new LoaderClassPath(loader));
            cp.insertClassPath(new ByteArrayClassPath(realClassName, classfileBuffer));
            CtClass cc = cp.get(realClassName);

            boolean classUpdated = false;
            for (ClassInstrumentationHandler handler : handlers) {
//                if (classUpdated = handler.transformed(cc, cp)) {
//                    break;
//                }
                handler.transformed(cc, cp);
            }
            return cc.toBytecode();

//            if (classUpdated) {
//                return cc.toBytecode();
//            } else {
//                return null;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }
}
