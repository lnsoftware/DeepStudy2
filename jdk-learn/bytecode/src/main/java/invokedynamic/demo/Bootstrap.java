package invokedynamic.demo;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.*;

import static org.objectweb.asm.Opcodes.*;

public class Bootstrap {
    private static void hello() {
        System.out.println("Hello!");
    }

    public static CallSite bootstrap(MethodHandles.Lookup caller, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class thisClass = lookup.lookupClass();
        MethodHandle mh = lookup.findStatic(thisClass, "hello", MethodType.methodType(void.class));
        return new ConstantCallSite(mh.asType(type));
    }
}