package com.zhiyin.jdi;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import com.sun.tools.jdi.SocketAttachingConnector;


import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotSwapper {

    public static void main(String[] args) throws Exception{
        List<Connector> connectors =
                Bootstrap.virtualMachineManager().allConnectors();
        SocketAttachingConnector sac = null;
        for (Connector connector : connectors) {
            if(connector instanceof SocketAttachingConnector) {
                sac = (SocketAttachingConnector)connector;
            }
        }
        if(sac != null) {
            Map<String, Connector.Argument> defaultArguments = sac.defaultArguments();
            Connector.Argument hostArg = defaultArguments.get("hostname");
            Connector.Argument portArg = defaultArguments.get("port");
            hostArg.setValue(DebugeeConfig.HotswapConfig.ServerHost);
            portArg.setValue(DebugeeConfig.HotswapConfig.ServerPort);
            VirtualMachine vm = sac.attach(defaultArguments);

            List<ReferenceType> rtList = vm.classesByName(DebugeeConfig.HotswapConfig.ClassName);
            ReferenceType rt = rtList.get(0);
            Map<ReferenceType, byte[]> newByteCodeMap = new HashMap<ReferenceType, byte[]>(1);
            byte[] newByteCode = genNewByteCodeUsingJavaCompiler();
            newByteCodeMap.put(rt, newByteCode);

            if(vm.canRedefineClasses()) {
                vm.redefineClasses(newByteCodeMap);
            }
        }
    }

    private static byte[] genNewByteCodeUsingJavaCompiler() throws Exception {
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//
//        File javaFile =
//                new File(DebugeeConfig.BreakpointClassPath);
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//        Iterable<? extends JavaFileObject> compilationUnit =
//                fileManager.getJavaFileObjectsFromFiles(Arrays.asList(javaFile));
//        compiler.getTask(null, fileManager, null, null, null, compilationUnit).call();

        File classFile =
                new File(DebugeeConfig.HotswapConfig.ClassFilePath);
        InputStream in = new FileInputStream(classFile);
        byte[] buf = new byte[(int)classFile.length()];
        while (in.read(buf) != -1) {}
        return buf;
    }
//
//    private static byte[] genNewByteCodeUsingJavassist() throws Exception {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("me.kisimple.just4fun.Foo");
//        CtMethod cm = cc.getDeclaredMethod("bar");
//        cm.setBody("{System.out.println(\"hello HotSwapper.\");}");
//        return cc.toBytecode();
//    }
}