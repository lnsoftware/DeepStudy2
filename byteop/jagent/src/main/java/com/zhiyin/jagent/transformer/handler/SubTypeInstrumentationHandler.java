package com.zhiyin.jagent.transformer.handler;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public abstract class SubTypeInstrumentationHandler implements ClassInstrumentationHandler {
    protected final String superTypeName;
    private boolean enabled = true;

    public SubTypeInstrumentationHandler(String superTypeName) {
        this.superTypeName = superTypeName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public final boolean transformed(CtClass cc, ClassPool pool) {
        try {
            if (canTransform(cc, pool)) {
//                log.info("start transform {}",);
                return transform(cc, pool);
            }
        } catch (NotFoundException | CannotCompileException | IOException e) {
            // Disable the handler for the remainder.
            log.error("Error instrumenting " + cc.getName() + ": " + e.toString() + " [" + getClass().getName() + "]");
            enabled = false;
        }

        return false;
    }

    public boolean canTransform(CtClass cc, ClassPool pool) throws NotFoundException {
        return isEnabled() && cc.subtypeOf(pool.get(superTypeName));
    }

    protected abstract boolean transform(CtClass cc, ClassPool pool) throws NotFoundException, CannotCompileException, IOException;
}
