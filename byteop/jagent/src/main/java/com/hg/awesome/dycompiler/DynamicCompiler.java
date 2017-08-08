package com.hg.awesome.dycompiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
public class DynamicCompiler {

    private static final String classOutputFolder = "/classpath/";

    public static class MyDiagnosticListener implements DiagnosticListener<JavaFileObject> {

        @Override
        public void report(Diagnostic<? extends JavaFileObject> diagnostic) {

        }
    }

    public static class InMemoryJavaFileObject extends SimpleJavaFileObject {

        private String contents = null;

        public InMemoryJavaFileObject(String className, String contents) throws Exception {
            super(URI.create("string:///" + className.replace('.', '/')
                    + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.contents = contents;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException {
            return contents;
        }
    }

    private static JavaFileObject getJavaFileObject() {
        StringBuilder contents = new StringBuilder(
                "package test;\n"
                + "  public class Main{\n"
                + "  public static void main(String[] args) { \n"
                + "  String name=");
        contents.append("\"");
        contents.append("deMarq\";\n");
        contents.append("System.out.println(name);\n");
        contents.append("}\n").append("}\n");
        JavaFileObject so = null;
        try {
            so = new InMemoryJavaFileObject("test.Main", contents.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return so;
    }

    public static void compile(Iterable<? extends JavaFileObject> files) {
        //System compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //Error output
        MyDiagnosticListener c = new MyDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c,
                Locale.ENGLISH,
                null);
        //class output
        Iterable options = Arrays.asList("-d", classOutputFolder);

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                c, options, null,
                files);
        Boolean result = task.call();
        if (result == true) {
            System.out.println("Compiled");
        }
    }

    public static void runIt() throws InvocationTargetException {

        File file = new File(classOutputFolder);

        try {
            // Convert File to a URL
            URL url = file.toURL(); // file:/classes/demo
            URL[] urls = new URL[]{url};
            System.out.println("url size : " + urls.length);

            ClassLoader loader = new URLClassLoader(urls);

            Class thisClass = loader.loadClass("test.Main");

            Class params[] = {};
            Object paramsObj[] = {};
            Object instance = thisClass.newInstance();
            Method[] methods = thisClass.getMethods();

            for (Method each : methods) {
                if (each.getName().contains("main")) {
                    each.invoke(instance, (Object) null);
                }
            }

        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) throws Exception {

        //dynamic class generate
        JavaFileObject file = getJavaFileObject();
        System.out.println(file);
        Iterable<? extends JavaFileObject> files = Arrays.asList(file);

        //compile Parth
        compile(files);

        //call class and run it
        runIt();
    }
}