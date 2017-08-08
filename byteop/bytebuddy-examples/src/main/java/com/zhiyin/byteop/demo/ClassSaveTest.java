package com.zhiyin.byteop.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.jar.asm.commons.Method;
import org.junit.Test;
import pl.halun.demo.bytebuddy.agent.examples.agents.regexlogger.LoggingInterceptor;
import pl.halun.demo.bytebuddy.agent.examples.agents.trycatch.TryCatchInterceptor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 保存bytecode生成的class文件
 * http://stackoverflow.com/questions/30859419/display-generated-bytebuddy-bytecode
 * Created by wangqinghui on 2017/2/17.
 */
public class ClassSaveTest {

    public static String savePath = "c:/temp";


    @Test
    public void simple() throws IOException {
        new ByteBuddy()
                .subclass(Object.class)
                .name("Demo1")
                .make()
                .saveIn(new File(savePath));
    }

    @Test
    public void change() throws IOException {
        new ByteBuddy()
                .subclass(Object.class)
                .name("Demo2")
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .saveIn(new File(savePath));
    }



    @Test
    public void saveAndRun() throws Exception {
        DynamicType.Unloaded<Object> build = new ByteBuddy()
                .subclass(Object.class)
                .name("Demo3")
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!")).make();

        build.saveIn(new File(savePath));

        Class<?> dynamicType = build.load(getClass().getClassLoader()).getLoaded();

        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }


    @Test
    public void inte() throws Exception {
        DynamicType.Unloaded<Object> build = new ByteBuddy()
                .subclass(Object.class)
                .name("Demo9")
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .method(named("hello2"))
//                .defineMethod("hellogen", String.class, Modifier.PUBLIC)
//                .withParameters(String.class)
                .intercept(FixedValue.value("Hello World!"))
//                .intercept(MethodDelegation.to(LoggingInterceptor.class))
                .make();


        build.saveIn(new File(savePath));

        Class<?> dynamicType = build.load(getClass().getClassLoader()).getLoaded();

        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }



    void doStuff() throws Exception {
        byte[] classBytes = new ByteBuddy()
                .subclass(Object.class)
                .name("MyClass")
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .getBytes();

        // store the MyClass.class file on the file system
        Files.write(Paths.get("MyClass.class"), classBytes, StandardOpenOption.CREATE);

        // create an instance of the class from the byte array
//        Class<?> defineClass = defineClass("MyClass", classBytes, 0, classBytes.length);
//        System.out.println(defineClass.newInstance());
    }



    public class HelloInterceptor {
        @RuntimeType
        public Object intercept(@AllArguments Object[] allArguments,
                                @Origin Method method) {
            // intercept any method of any signature
            return "";
        }

//        @RuntimeType
        public Object greet(Object argument) {
            return "Hello from " + argument;
        }
    }

}
