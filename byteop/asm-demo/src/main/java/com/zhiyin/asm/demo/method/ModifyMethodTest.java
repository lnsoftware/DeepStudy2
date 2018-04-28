package com.zhiyin.asm.demo.method;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.FileOutputStream;

public class ModifyMethodTest {
    @Test
    public void modiySleepMethod() throws Exception {  
        ClassReader classReader = new ClassReader(
                Person.class.getName());
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

//        CheckClassAdapter ca = new CheckClassAdapter(cw);

        FaultInjectVisitor classAdapter = new FaultInjectVisitor(classWriter, "");
        classReader.accept(classAdapter, ClassReader.SKIP_DEBUG);  
  
        byte[] classFile = classWriter.toByteArray();

        FileOutputStream fos = new FileOutputStream("./Demo2.class");
        fos.write(classFile);
        fos.close();
        MyClassLoad classLoader = new MyClassLoad();
//        @SuppressWarnings("rawtypes")
        Class clazz = classLoader.defineClass(
                Person.class.getName(), classFile);
        Object obj = clazz.newInstance();
        System.out.println(clazz.getDeclaredField("name").get(obj));
        clazz.getDeclaredMethod("sleep").invoke(obj);
    }  
}  