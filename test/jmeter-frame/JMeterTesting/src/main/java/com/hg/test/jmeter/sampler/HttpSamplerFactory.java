package com.hg.test.jmeter.sampler;

import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.testelement.TestElement;

/**
 * Created by hg on 2016/9/30.
 */
public class HttpSamplerFactory {


    public static HTTPSamplerProxy google(){
        // First HTTP Sampler - open example.com
        HTTPSamplerProxy examplecomSampler = new HTTPSamplerProxy();
        examplecomSampler.setDomain("www.google.com");
        examplecomSampler.setPort(80);
        examplecomSampler.setPath("/");
        examplecomSampler.setMethod("GET");
        examplecomSampler.setName("Open example.com");
        examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());

        return examplecomSampler;
    }


    public static HTTPSamplerProxy demoWebOk(){
        // First HTTP Sampler - open example.com
        HTTPSamplerProxy examplecomSampler = new HTTPSamplerProxy();
        examplecomSampler.setDomain("localhost");
        examplecomSampler.setPort(8080);
        examplecomSampler.setPath("/test/ok");
        examplecomSampler.setMethod("GET");
        examplecomSampler.setName("LocalhostOk");
        examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());

        return examplecomSampler;
    }

}
