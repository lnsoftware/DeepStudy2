package com.zhiyin.jagent.transformer.handler;

import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class LogbackTraceLoggerHandler extends SubTypeInstrumentationHandler {

    public static String LogbackLogger = "ch.qos.logback.classic.Logger";
    public static String methodName= "buildLoggingEventAndAppend";
    public LogbackTraceLoggerHandler() {
        super( LogbackLogger);
    }

    protected boolean transform(CtClass cc, ClassPool pool) throws NotFoundException, CannotCompileException, IOException {

        log.info("proce logback");

        CtMethod serviceMethod;
        try {
            serviceMethod = cc.getDeclaredMethod("buildLoggingEventAndAppend" );
        } catch (NotFoundException e) {
            // Not every servlet will implement this method and that's okay.
            return false;
        }

        // Copy the method to a uniquely named location that won't conflict with anything.
        CtMethod copiedServiceMethod = CtNewMethod.copy(serviceMethod, cc.makeUniqueName( methodName ), cc, null);
        copiedServiceMethod.setModifiers(Modifier.PRIVATE);
        cc.addMethod(copiedServiceMethod);

        // Swap in a new method body for service() that invokes the copied version of service().
        // 文件位置和当前class的路径一致
//        final String source = Resources.toString(Resources.getResource(getClass(), "logback-json-logger.javassist"), Charset.forName("utf-8"));
        InputStream schemaIS = this.getClass().getClassLoader().getResourceAsStream("META-INF/agent/handler/logback-json-logger.javassist");
        String source = readInputStreamAsString(schemaIS);
        System.out.println(source);
        pool.importPackage("org.slf4j");

        serviceMethod.setBody(source, "this", copiedServiceMethod.getName());

        return true;
    }


    public static String readInputStreamAsString(InputStream in)
            throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            byte b = (byte)result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }
}
