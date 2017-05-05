package com.hg.dubbo.study.extension;

import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.extension.factory.AdaptiveExtensionFactory;
import com.alibaba.dubbo.rpc.Protocol;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by hg on 2016/8/29.
 */
@Slf4j
public class FactoryTest {

    @Test
    public void factory() {

        //ExtensionFactory的loader
        ExtensionLoader<ExtensionFactory> extensionFactoryExtensionLoader =
                ExtensionLoader.getExtensionLoader(ExtensionFactory.class);

//        AdaptiveExtensionFactory有@adaptive注解
//        ExtensionFactory adaptive = factoryExtensionLoader.getAdaptiveExtension();

        // SpiExtensionFactory
        ExtensionFactory extension = extensionFactoryExtensionLoader.getExtension("spi");

        log.info("extension name:{}");
    }

    @Test
    public void dubbo() {

        ExtensionLoader<Protocol> protocolExtensionLoader =
                ExtensionLoader.getExtensionLoader(Protocol.class);
//        首先 Protocol 类带有 SPI 注解，因此我们可以确认默认是使
//        用 dubbo=com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol 作为默认扩展点；
        // 没有adaptive实现类，需要用代码生成
        Protocol adaptiveExtension = protocolExtensionLoader.getAdaptiveExtension();

//        protocolExtensionLoader.ge

        Protocol protocolExtension= protocolExtensionLoader.getExtension("dubbo");

        log.info("{}",protocolExtension.getDefaultPort());

    }


    @Test
    public void compile() throws Exception {

        String code = helloCode();
        ClassLoader classLoader = this.getClass().getClassLoader();
        com.alibaba.dubbo.common.compiler.Compiler compiler = ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.compiler.Compiler.class).getAdaptiveExtension();

        Class<?> clazz = compiler.compile(code, classLoader);

        Method method = clazz.getMethod("hello");

        Object obj = clazz.newInstance();

        method.invoke(obj);

//        log.info();
    }

    public String helloCode(){
        return "package com.alibaba.dubbo.rpc;\n public class Hello$Hello { " +
                "public void hello(){" +
                "System.out.println(\"hello\");" +
                "}" +
                "}";
       /* return "\n" +
                "package com.alibaba.dubbo.rpc;\n" +
                "import com.alibaba.dubbo.common.extension.ExtensionLoader;\n" +
                "public class Protocol$Adpative implements com.alibaba.dubbo.rpc.Protocol {\n" +
                "public void destroy() {throw new UnsupportedOperationException(\"method public abstract void com.alibaba.dubbo.rpc.Protocol.destroy() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!\");\n" +
                "}\n" +
                "public int getDefaultPort() {throw new UnsupportedOperationException(\"method public abstract int com.alibaba.dubbo.rpc.Protocol.getDefaultPort() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!\");\n" +
                "}\n" +
                "public com.alibaba.dubbo.rpc.Exporter export(com.alibaba.dubbo.rpc.Invoker arg0) throws com.alibaba.dubbo.rpc.RpcException {\n" +
                "if (arg0 == null) throw new IllegalArgumentException(\"com.alibaba.dubbo.rpc.Invoker argument == null\");\n" +
                "if (arg0.getUrl() == null) throw new IllegalArgumentException(\"com.alibaba.dubbo.rpc.Invoker argument getUrl() == null\");com.alibaba.dubbo.common.URL url = arg0.getUrl();\n" +
                "String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
                "if(extName == null) throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(\" + url.toString() + \") use keys([protocol])\");\n" +
                "com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);\n" +
                "return extension.export(arg0);\n" +
                "}\n" +
                "public com.alibaba.dubbo.rpc.Invoker refer(java.lang.Class arg0, com.alibaba.dubbo.common.URL arg1) throws com.alibaba.dubbo.rpc.RpcException {\n" +
                "if (arg1 == null) throw new IllegalArgumentException(\"url == null\");\n" +
                "com.alibaba.dubbo.common.URL url = arg1;\n" +
                "String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
                "if(extName == null) throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(\" + url.toString() + \") use keys([protocol])\");\n" +
                "com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);\n" +
                "return extension.refer(arg0, arg1);\n" +
                "}\n" +
                "}";
                */
    }
}
